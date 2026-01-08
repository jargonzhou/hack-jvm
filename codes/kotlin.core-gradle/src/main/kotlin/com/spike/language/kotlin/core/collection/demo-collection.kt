package com.spike.language.kotlin.core.collection

import com.spike.language.kotlin.core.oo.Student
import java.util.*

/**
 * Demonstration of Collections
 *
 * @author zhoujiagen
 */

fun main(args: Array<String>) {
    println(ArrayList<String>()::class)
    println(Collections.singletonList(1)::class)


    // ==================================================== array
    val intArray = kotlin.arrayOf(1, 2, 3, 4)
    println(intArray.joinToString(","))
    val intArray2 = intArrayOf(1, 2, 3, 4, 5)
    println(intArray2.joinToString(","))
    for (value in intArray) { // loop element
        println("$value")
    }
    for (index in intArray.indices) { // loop with index
        println("${intArray[index]}")
    }
    // multiple extension methods
    println("First: ${intArray.first()}, Last: ${intArray.last()}")
    println(intArray.take(3))
    println("${intArray.map { e -> "Item " + e.toString() }.joinToString(",")}")
    val tripleIntArray = intArray.flatMap { e -> arrayOf(e, e, e).asIterable() }
    println(tripleIntArray.joinToString(","))
    // cast to collection
    println(intArray.toHashSet())
    println(intArray.toSortedSet())
    println(intArray.toSet())
    println(intArray.toMutableSet())
    println(intArray.toList())
    println(intArray.toMutableList())
    // destruction
    val (e1, e2, e3, e4) = intArray
    println("e1=$e1, e2=$e2...")


    val stringArray = kotlin.arrayOfNulls<String>(3)
    stringArray[0] = "Java"
    stringArray[1] = "Scala"
    stringArray[2] = "Kotlin"

    val studentArray = Array<Student>(2) { index: Int ->
        when (index) {
            0 -> Student(1, "af", "al")
            1 -> Student(2, "bf", "bl")
            else -> throw IllegalArgumentException("too many elements")
        }
    }
    println(studentArray.joinToString("\n"))

    val longArray = emptyArray<Long>()
    // longArray[1] = 1L // raise ArrayIndexOutOfBoundsException
    println(longArray.joinToString(","))


    // ==================================================== list
    val intList = listOf(1, 2, 3)
    println(intList)
    println(intList.asReversed())

    // ==================================================== map
    val map1 = mapOf("a" to "Alice", "b" to "Bob", "c" to "Cartman")
    println(map1)
    println(map1.mapKeys { key -> "|Key: ${key}|" })
    for ((key, value) in map1) { // loop with key and value
        println("$key=$value")
    }


    // ==================================================== set

    val set1 = setOf(1, 2, 3, 1, 2, 3)
    println(set1)

    // ==================================================== sequence: java 8 Stream
    val sequence1 = generateSequence(100) {
        //println(it)
        if (it % 2 == 0) {
            it + 2
        } else {
            it + 1
        }
    }
    println(sequence1.take(10).joinToString(""))

    val fibonacci = generateSequence(1 to 1) {
        it.second to it.first + it.second
    }.map { it.first }
    println(fibonacci.take(10).joinToString(" "))
}


