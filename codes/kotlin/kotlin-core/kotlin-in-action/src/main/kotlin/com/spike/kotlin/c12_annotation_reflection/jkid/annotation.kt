package com.spike.kotlin.c12_annotation_reflection.jkid

import kotlin.reflect.KClass

@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FIELD) // meta-annotation
annotation class JsonExclude

annotation class JsonName(val name: String)

annotation class DeserializeInterface(val targetClass: KClass<out Any>)

annotation class CustomSerializer(val serializerClass: KClass<out ValueSerializer<*>>)
