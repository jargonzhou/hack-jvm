package com.spike.kotlin.c12_annotation_reflection.jkid

import java.util.*
import kotlin.reflect.*
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

// =============================================================================
// serialize fun based on StringBuilder
// =============================================================================

fun serialize(obj: Any): String = buildString {
    serializeObject(obj)
}

private fun StringBuilder.serializeObject(obj: Any) {
    @Suppress("UNCHECKED_CAST")
    val kClass = obj::class as KClass<Any>
    append("{")
    kClass.memberProperties
        // @JsonExclude
        .filter { it.findAnnotation<JsonExclude>() == null }
        .joinTo(this, separator = ", ") {
            serializeProperty(it, obj)
            "" // default value
        }
    append("}")
}

private fun StringBuilder.serializeProperty(property: KProperty1<Any, *>, obj: Any) {
    // @JsonName
    val jsonNameAnn = property.findAnnotation<JsonName>()
    val propName = jsonNameAnn?.name ?: property.name
    serializeString(propName)
    append(": ")

    val value = property.get(obj)
    val jsonValue = property.getSerializer()?.toJsonValue(value) ?: value
    serializePropertyValue(jsonValue)
}

fun KProperty<*>.getSerializer(): ValueSerializer<Any?>? {
    // @CustomSerializer
    val customSerializerAnn = findAnnotation<CustomSerializer>() ?: return null
    val serializerClass = customSerializerAnn.serializerClass

    val valueSerializer = serializerClass.objectInstance ?: serializerClass.createInstance()
    @Suppress("UNCHECKED_CAST")
    return valueSerializer as ValueSerializer<Any?>
}

private fun StringBuilder.serializePropertyValue(value: Any?) {
    when (value) {
        null -> append("null")
        is String -> serializeString(value)
        is Number, is Boolean -> append(value.toString())
        is List<*> -> serializeList(value)
        else -> serializeObject(value)
    }
}

private fun StringBuilder.serializeList(data: List<Any?>) {
    append("[")
    data.forEach { serializePropertyValue(it) }
    append("]")
}

private fun StringBuilder.serializeString(s: String) {
    append('\"')
    s.forEach { append(it.escape()) }
    append('\"')
}

private fun Char.escape(): Any =
    when (this) {
        '\\' -> "\\\\"
        '\"' -> "\\\""
        '\b' -> "\\b"
        '\u000C' -> "\\f"
        '\n' -> "\\n"
        '\r' -> "\\r"
        '\t' -> "\\t"
        else -> this
    }

// =============================================================================
// value serializer: primitive type, string
// =============================================================================

interface ValueSerializer<T> {
    fun toJsonValue(value: T): Any?
    fun fromJsonValue(jsonValue: Any?): T
}

object ValueSerializers {
    fun serializerForBasicType(type: KType): ValueSerializer<out Any?> {
        assert(type.isPrimitiveOrString()) { "Expected primitive type or String: $type" }
        return serializerForType(type)!!
    }

    fun serializerForType(type: KType): ValueSerializer<out Any?>? =
        when (type) {
            typeOf<Byte>() -> ByteSerializer
            typeOf<Short>() -> ShortSerializer
            typeOf<Int>() -> IntSerializer
            typeOf<Long>() -> LongSerializer
            typeOf<Float>() -> FloatSerializer
            typeOf<Double>() -> DoubleSerializer
            typeOf<Boolean>() -> BooleanSerializer
            typeOf<String>(),
            typeOf<String?>() -> StringSerializer

            else -> null
        }
}

object ByteSerializer : ValueSerializer<Byte> {
    override fun fromJsonValue(jsonValue: Any?) = jsonValue.expectNumber().toByte()
    override fun toJsonValue(value: Byte) = value
}

object ShortSerializer : ValueSerializer<Short> {
    override fun fromJsonValue(jsonValue: Any?) = jsonValue.expectNumber().toShort()
    override fun toJsonValue(value: Short) = value
}

object IntSerializer : ValueSerializer<Int> {
    override fun fromJsonValue(jsonValue: Any?) = jsonValue.expectNumber().toInt()
    override fun toJsonValue(value: Int) = value
}

object LongSerializer : ValueSerializer<Long> {
    override fun fromJsonValue(jsonValue: Any?) = jsonValue.expectNumber().toLong()
    override fun toJsonValue(value: Long) = value
}

object FloatSerializer : ValueSerializer<Float> {
    override fun fromJsonValue(jsonValue: Any?) = jsonValue.expectNumber().toFloat()
    override fun toJsonValue(value: Float) = value
}

object DoubleSerializer : ValueSerializer<Double> {
    override fun fromJsonValue(jsonValue: Any?) = jsonValue.expectNumber().toDouble()
    override fun toJsonValue(value: Double) = value
}

object BooleanSerializer : ValueSerializer<Boolean> {
    override fun fromJsonValue(jsonValue: Any?): Boolean {
        if (jsonValue !is Boolean) throw JKidException("Expected boolean, was: $jsonValue")
        return jsonValue
    }

    override fun toJsonValue(value: Boolean) = value
}

object StringSerializer : ValueSerializer<String?> {
    override fun fromJsonValue(jsonValue: Any?): String? {
        if (jsonValue !is String?) throw JKidException("Expected string, was: $jsonValue")
        return jsonValue
    }

    override fun toJsonValue(value: String?) = value
}

class DateSerializer : ValueSerializer<Date> {
    override fun toJsonValue(value: Date): Any? {
        TODO("Not yet implemented")
    }

    override fun fromJsonValue(jsonValue: Any?): Date {
        TODO("Not yet implemented")
    }
}