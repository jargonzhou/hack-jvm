/**
 * Demonstration of overriding rules: open, abstract, override.
 *
 * Method should be explicitly mark as open, which can be overrided
 */
package com.spike.language.kotlin.core.oo

import java.io.OutputStream

// ======================================================================= verride abstract method, and mark as final
abstract class SingleEngineAirplane protected constructor() {
    abstract fun fly()
}

class CesnaAirplane : SingleEngineAirplane() {
    // override abstract method, and mark as final
    final override fun fly() {
        println("fly a cesna")
    }
}

// ======================================================================= override val with var
open class Base {
    // should be explicitly mark as open, which can be overrided
    open val property1: String
        get() = "Base::value"
}

class DerivedA : Base() {
    override val property1: String
        get() = "Base::value"
}

class DerivedB : Base() {
    private var _property1: String = ""
    // we can also override val with var
    override var property1: String
        get() = _property1
        set(value) {
            _property1 = value
        }
}

// ======================================================================= call super method

open class Image {
    open fun save(output: OutputStream) {
        println("Image::save")
    }
}

interface VendorImage {
    fun save(output: OutputStream) {
        println("Vendor::save")
    }
}

// call class constructor to distinguish class and interface
class PNGImage : Image(), VendorImage {
    override fun save(output: OutputStream) {
        super<VendorImage>.save(output)
        super<Image>.save(output)
        println("PNGImage::save")
    }
}

