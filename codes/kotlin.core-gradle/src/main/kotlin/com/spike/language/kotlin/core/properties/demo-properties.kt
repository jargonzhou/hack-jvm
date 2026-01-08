/**
 * Demonstration of properties from .NET world.
 * TODO(zhoujiagen) continue here 20180509
 */
package com.spike.language.kotlin.core.properties

class Student(name: String, age:Int) {
    public var Name = ""
    set(value) {
        field = value // special variable: 'field', 'value'
    }

    public var Age = 20
    set(value) {
        field = value
    }

    // consutructor method, should be defined after properties
    init {
        Name = name
        Age = age
    }
}

fun main(args: Array<String>) {
    val student = Student("Jamie Fox", 20)
    println("${student.Name} is ${student.Age} years old")
    student.Age+=1
    println("${student.Name} is ${student.Age} years old")
}