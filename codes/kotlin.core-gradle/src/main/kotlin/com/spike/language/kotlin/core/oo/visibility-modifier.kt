/**
 * Demonstration of visibility modifier usage on class.
 */
package com.spike.language.kotlin.core.oo

import java.lang.reflect.Modifier

open class Container {
    // open: can be overrided
    protected open val fieldA: String = "Some Value"
}

class DerivedContainer : Container() { // must use constructor of Container
    // change the visibility modifier to 'public'
    public override val fieldA: String = "Something else"
}


fun main(args: Array<String>) {
    val derivedContainer = DerivedContainer()
    println(derivedContainer.fieldA)
    val container: Container = derivedContainer
//    println(container.fieldA) // can not access

    // superclass info
    derivedContainer.javaClass.superclass.declaredFields.forEach { field ->
        field.isAccessible = true
        // why 'private final'???
        println("""Field: ${field.name},
            | Modifier: ${Modifier.toString(field.modifiers)},
            | Value: ${field.get(derivedContainer)}""".trimMargin())
    }

    derivedContainer.javaClass.declaredFields.forEach { field ->
        field.isAccessible = true
        println("""Field: ${field.name},
            | Modifier: ${Modifier.toString(field.modifiers)},
            | Value: ${field.get(derivedContainer)}""".trimMargin())
    }

}