package com.spike.kotlin.c11_generics

import kotlin.random.Random
import kotlin.reflect.KClass

fun printFirst(list: List<*>) {
    if (list.isNotEmpty()) {
        val first = list.first() // of type Any?
        println(first)
    }
}

//
// validators
//

interface FieldValidator<in T> {
    fun validate(input: T): Boolean
}

object DefaultStringValidator : FieldValidator<String> {
    override fun validate(input: String) = input.isNotEmpty()
}

object DefaultIntValidator : FieldValidator<Int> {
    override fun validate(input: Int) = input >= 0
}

object Validators {
    // KClass represent a Kotlin class
    private val validators =
        mutableMapOf<KClass<*>, FieldValidator<*>>() // *

    fun <T : Any> registerValidator(
        kClass: KClass<T>,
        fieldValidator: FieldValidator<T>
    ) {
        validators[kClass] = fieldValidator
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T : Any> get(kClass: KClass<T>): FieldValidator<T> =
        validators[kClass] as? FieldValidator<T> // as?
            ?: throw IllegalArgumentException("No validator for ${kClass.simpleName}")
}


fun main() {
    val list: MutableList<Any?> = mutableListOf('a', 1, "qwe")
    val chars = mutableListOf('a', 'b', 'c')
    val unknownElements: MutableList<*> = if (Random.nextBoolean()) list else chars
    // can NOT put element into it - in
//    ERROR: Receiver type 'MutableList<*>' contains star projection which prohibits the use of 'fun add(element: E): Boolean'.
//    unknownElements.add(42)

    // can get element from it: as an out-projected type - out
    println(unknownElements.first()) // a

    printFirst(unknownElements) // a

    //
    // validators
    //
    val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()
    validators[String::class] = DefaultStringValidator
    validators[Int::class] = DefaultIntValidator
//    ERROR: Receiver type 'FieldValidator<*>' contains star projection which prohibits the use of 'fun validate(input: T): Boolean'.
//    validators[String::class]!!.validate(42)

    // Unchecked cast of 'FieldValidator<*>?' to 'FieldValidator<String>'.
    val stringValidator =
        validators[String::class] as FieldValidator<String>
    println(stringValidator.validate("")) // false

    val stringValidator2 =
        validators[Int::class] as FieldValidator<String>
    try {
        println(stringValidator2.validate(""))
    } catch (e: ClassCastException) {
        println(e.javaClass.name) // java.lang.ClassCastException
    }

    Validators.registerValidator(String::class, DefaultStringValidator)
    Validators.registerValidator(Int::class, DefaultIntValidator)

    println(Validators[String::class].validate("Kotlin")) // true
    println(Validators[Int::class].validate(42)) // true

//    ERROR: Argument type mismatch: actual type is 'Int', but 'String' was expected.
//    Validators[String::class].validate(42)
}