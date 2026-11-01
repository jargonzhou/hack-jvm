package com.spike.kotlin.c12_annotation_reflection.jkid

import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.KType
import kotlin.reflect.full.*
import kotlin.reflect.javaType

inline fun <reified T : Any> deserialize(json: String): T {
    return deserialize(json, T::class)
}

fun <T : Any> deserialize(json: String, targetClass: KClass<T>): T {
    val seed = ObjectSeed(targetClass, ClassInfoCache())
    Parser(json, seed).parse()
    return seed.spawn()
}

// =============================================================================
// json object
// =============================================================================

interface JsonObject {
    fun setSimpleProperty(name: String, value: Any?)

    fun createObject(name: String): JsonObject

    fun createArray(name: String): JsonObject
}

interface Seed : JsonObject {
    val classInfoCache: ClassInfoCache

    fun spawn(): Any?
    fun createCompositeProperty(name: String, isList: Boolean): JsonObject
    override fun createObject(name: String) = createCompositeProperty(name, false)
    override fun createArray(name: String) = createCompositeProperty(name, true)
}

private fun isSubtypeOfList(type: KType): Boolean {
    val listType: KType = List::class.starProjectedType // List<*>
    return type.isSubtypeOf(listType)
}

private fun getParameterizedType(type: KType): KType {
    return type.arguments.single().type!!
}

fun Seed.createSeedForType(paramType: KType, isList: Boolean): Seed {
    val paramClass = paramType.classifier as KClass<out Any>
    if (isSubtypeOfList(paramType)) {
        println("It's a list!")
        if (!isList) throw JKidException("An array expected, not a composite object.")

        val elementType = getParameterizedType(paramType)
        if (elementType.isPrimitiveOrString()) {
            return ValueListSeed(elementType, classInfoCache)
        }
        return ObjectListSeed(elementType, classInfoCache)
    }
    if (isList) throw JKidException("Object of the type $paramType expected, not an array")
    return ObjectSeed(paramClass, classInfoCache)
}

class ObjectSeed<out T : Any>(
    targetClass: KClass<T>,
    override val classInfoCache: ClassInfoCache
) : Seed {
    private val classInfo: ClassInfo<T> = classInfoCache[targetClass]

    private val valueArguments = mutableMapOf<KParameter, Any?>()
    private val seedArguments = mutableMapOf<KParameter, Seed>()

    private val arguments: Map<KParameter, Any?>
        get() = valueArguments + seedArguments.mapValues { it.value.spawn() }

    override fun setSimpleProperty(name: String, value: Any?) {
        val param = classInfo.getConstructorParameter(name)
        valueArguments[param] = classInfo.deserializeConstructorArgument(param, value)
    }

    override fun spawn(): T = classInfo.createInstance(arguments)

    override fun createCompositeProperty(name: String, isList: Boolean): JsonObject {
        val param = classInfo.getConstructorParameter(name)
        val deserializedAs = classInfo.getDeserializeClass(name)?.starProjectedType
        val seed = createSeedForType(deserializedAs ?: param.type, isList)
        return seed.apply { seedArguments[param] = this }
    }
}

class ObjectListSeed(
    val elementType: KType,
    override val classInfoCache: ClassInfoCache
) : Seed {
    private val elements = mutableListOf<Seed>()

    override fun setSimpleProperty(name: String, value: Any?) {
        throw JKidException("Found primitive value in collection of object types")
    }

    override fun createCompositeProperty(name: String, isList: Boolean) =
        createSeedForType(elementType, isList).apply { elements.add(this) }

    override fun spawn(): List<*> = elements.map { it.spawn() }
}

class ValueListSeed(
    elementType: KType,
    override val classInfoCache: ClassInfoCache
) : Seed {
    private val elements = mutableListOf<Any?>()
    private val serializerForType = ValueSerializers.serializerForBasicType(elementType)

    override fun setSimpleProperty(name: String, value: Any?) {
        elements.add(serializerForType.fromJsonValue(value))
    }

    override fun createCompositeProperty(name: String, isList: Boolean): Seed {
        throw JKidException("Found object value in collection of primitive types")
    }

    override fun spawn() = elements
}
// =============================================================================
// class info
// =============================================================================

class ClassInfoCache {
    private val cacheData = mutableMapOf<KClass<*>, ClassInfo<*>>()

    @Suppress("UNCHECKED_CAST")
    operator fun <T : Any> get(cls: KClass<T>): ClassInfo<T> =
        cacheData.getOrPut(cls) { ClassInfo(cls) } as ClassInfo<T>
}

class ClassInfo<T : Any>(cls: KClass<T>) {
    private val className = cls.qualifiedName
    private val constructor = cls.primaryConstructor
        ?: throw JKidException("Class $className doesn't have a primary constructor")
    private val jsonNameToParamMap = hashMapOf<String, KParameter>()
    private val paramToSerializerMap = hashMapOf<KParameter, ValueSerializer<out Any?>>()
    private val jsonNameToDeserializerClassMap = hashMapOf<String, KClass<out Any>?>()

    init {
        constructor.parameters.forEach { cacheDataForParameter(cls, it) }
    }

    private fun cacheDataForParameter(cls: KClass<*>, param: KParameter) {
        val paramName = param.name ?: throw JKidException("Class $className has constructor parameter without name")
        val property = cls.declaredMemberProperties.find { it.name == paramName } ?: return
        val name = property.findAnnotation<JsonName>()?.name ?: paramName
        jsonNameToParamMap[name] = param

        val deserializedClass = property.findAnnotation<DeserializeInterface>()?.targetClass
        jsonNameToDeserializerClassMap[name] = deserializedClass

        val valueSerializer = property.getSerializer()
            ?: ValueSerializers.serializerForType(param.type)
            ?: return
        paramToSerializerMap[param] = valueSerializer
    }

    fun getConstructorParameter(name: String): KParameter = jsonNameToParamMap[name]
        ?: throw JKidException("Constructor parameter $name is not found for class $className")

    fun getDeserializeClass(name: String) = jsonNameToDeserializerClassMap[name]

    fun deserializeConstructorArgument(param: KParameter, value: Any?): Any? {
        val serializer = paramToSerializerMap[param]
        if (serializer != null) {
            return serializer.fromJsonValue(value)
        }

        validateArgument(param, value)
        return value
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun validateArgument(param: KParameter, value: Any?) {
        println("Validating $param with value $value")
        if (value == null && !param.type.isMarkedNullable) {
            throw JKidException("Received null value for non-nullable parameter ${param.name}")
        }
        if (value != null && value.javaClass != param.type.javaType) {
            throw JKidException("Type mismatch for parameter ${param.name}: expected ${param.type.javaType}, found ${value.javaClass}")
        }
    }

    fun createInstance(arguments: Map<KParameter, Any?>): T {
        ensureAllParametersPresent(arguments)
        return constructor.callBy(arguments)
    }

    private fun ensureAllParametersPresent(arguments: Map<KParameter, Any?>) {
        for (param in constructor.parameters) {
            if (arguments[param] == null && !param.isOptional && !param.type.isMarkedNullable) {
                throw JKidException("Missing value for parameter ${param.name}")
            }
        }
    }
}
