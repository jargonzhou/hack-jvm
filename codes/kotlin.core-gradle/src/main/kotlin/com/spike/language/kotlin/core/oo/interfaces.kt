/**
 * Demonstration of interfaces.
 *
 * interfaces in Kotlin are just like in Java 8, can contain properties.
 */
package com.spike.language.kotlin.core.oo

import java.io.InputStream
import java.io.OutputStream

interface Document {
    val version: Long // properties
    val size: Long

    val name: String
        get() = "NoName" // default implementation

    fun save(input: InputStream)
    fun load(stream: OutputStream)
    // method with default implementation
    fun getDescription(): String {
        return "Document $name has $size byte(-s)"
    }
}

class DocumentImpl : Document {
    override val size: Long
        get() = 0

    override fun load(stream: OutputStream) {
    }

    override fun save(input: InputStream) {
    }

    override val version: Long
        get() = 0
}






















