package com.spike.kotlin.c12_annotation_reflection.jkid

import kotlin.reflect.KType
import kotlin.reflect.typeOf

class JKidException(message: String) : Exception(message)

internal fun Any?.expectNumber(): Number {
    if (this !is Number) throw JKidException("Expected number, was: $this")
    return this
}

internal fun KType.isPrimitiveOrString(): Boolean {
    val types = setOf(
        typeOf<Byte>(),
        typeOf<Short>(),
        typeOf<Int>(),
        typeOf<Long>(),
        typeOf<Float>(),
        typeOf<Double>(),
        typeOf<Boolean>(),
        typeOf<String>(),
        typeOf<String?>(),
    )
    return this in types
}
