package com.spike.kotlin.c12_annotation_reflection

import com.spike.kotlin.c12_annotation_reflection.jkid.serialize
import kotlin.reflect.full.memberProperties

class PersonR(val name: String, val age: Int)

fun foo(x: Int) = println(x)

fun sum(x: Int, y: Int) = x + y

// top-level properties
var counter = 0

fun main() {
    val person = PersonR("Alice", 29)
    val kClass = person::class
    println(kClass.simpleName) // PersonR
    kClass.memberProperties.forEach { println(it.name) }
//    age
//    name

    val kFunction = ::foo // reference of KFunction1<Int, Unit>
    kFunction.call(42) // 42

    val sumFun = ::sum
    println(sumFun.invoke(1, 2) + sumFun(3, 4)) // 10

    val kProperty = ::counter // reference of KMutableProperty0<Int>
    kProperty.setter.call(21)
    println(kProperty.get()) // 21

    val memberProperty = PersonR::age // reference of KProperty1<PersonR, Int>
    println(memberProperty.get(person)) // 29

    //
    // JKid
    //
    println(serialize(person)) // {"age": 29, "name": "Alice"}
}