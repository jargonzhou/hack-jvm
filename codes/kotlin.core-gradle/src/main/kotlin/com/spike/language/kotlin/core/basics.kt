package com.spike.language.kotlin.core

import java.io.BufferedReader
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

/**
 * A simple function to add 1 to an integer.
 * @param x an integer
 * @return [x]+1
 */
fun plusOne(x: Int) = x + 1

/**
 * Read content of a file.
 * @param path absolute path of file
 * @return content of the file at [path]
 */
fun readFile(path: String): String {
    val sb = StringBuilder()

    var reader: BufferedReader? = null
    try {
        reader = Files.newBufferedReader(Paths.get(path))
        var line: String? = reader.readLine()
        while (line != null) {
            sb.append(line).append(System.getProperty("line.separator"))
            line = reader.readLine()
        }
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        if (reader != null)
            reader.close()
    }
    return sb.toString()
}

/**
 * Same as [readFile] except that throw exceptions.
 */
@Throws(IOException::class)
fun readFileWithException(path: String): String {
    val sb = StringBuilder()

    var reader = Files.newBufferedReader(Paths.get(path))
    var line: String? = reader.readLine()
    while (line != null) {
        sb.append(line).append(System.getProperty("line.separator"))
        line = reader.readLine()
    }

    if (reader != null)
        reader.close()

    return sb.toString()
}


class Person(val name: String) {
    // this expression
    fun printMe() = println(this)
}

class Building(val address: String) {

    inner class Reception(telephone: String) {
        // use this to refer outer class
        // label: @
        fun printAddress() = println(this@Building.address)
    }

}

fun main(args: Array<String>) {
    // ======================================================================= val and vars
    val name = "Kotlin"
    var name2 = "Kotlin"
    name2 = "more Kotlin"
    println("name=$name, name2=$name2")

    // ======================================================================= type inference
    println("plusOne(2)=${plusOne(2)}")
    val explictType: Number = 12.3
    println(explictType)

    // ======================================================================= basic types: Double, Float, Long, Int, Short, Byte
    // explicit conversion
    val b: Byte = 1
    val i: Int = b.toInt()
    println("b=$b[${b::class}, ${b.javaClass.canonicalName}], i=$i[${i::class}, ${i.javaClass.canonicalName}]")
    // Char, Boolean
    // Array.constructor(size: Int, init: (Int) -> T)
    val intArray = Array(5, { i -> i + 1 })
    println(intArray.joinToString(", "))
    val intArray2 = intArrayOf(1, 2, 3, 4, 5)
    println(intArray2.joinToString(", "))
    // String
    val s = "hello, kotlin!"
    val text = """
        | what is real
        | is ration, and
        | what is rational is real.
    """.trimMargin()
    println("s=$s, text=$text")


    // ======================================================================= range: .., downTo, step, util
    val aToz = "a".."z"
    println("a" in aToz) // true
    println("z" in aToz) // true
    // kotlin.ranges.IntRange
    for (ii in 1..3) { // ..: [, ]
        print(ii)
    } // 123
    println()
//    println(ii) // compile failed
    for (ii in 3 downTo 1) {
        print(ii)
    } // 321
    println()
    for (ii in 1..3 step 2) {
        print(ii)
    } // 13
    println()
    for (ii in 1 until 5 step 2) { // until: [, )
        print(ii)
    } // 13
    println()


    // ======================================================================= loops: while, for
    var count = 10
    while (count > 0) {
        print("$count.")
        count--
    }
    println()
    for (i in listOf(1, 2, 3, 4)) {
        print("$i.")
    }
    println()

    // ======================================================================= exception handling: try-catch-finally
    val filepath = "src/main/kotlin/com/spike/language/kotlin/core/basics.kt"
    println(readFile(filepath))


    // ======================================================================= equality test
    // reference: ===, !==
    val patha = Paths.get("demo.txt")
    val pathb = Paths.get("demo.txt")
    println(patha === pathb) // false
    // structure: ==, !=
    println(patha == pathb) // true

    // ======================================================================= this expression
    Person("Cartman").printMe()
    // scope
    val building = Building("An address")
    building.Reception("123").printAddress()

    // ======================================================================= control flow as expression: if..else, try..catch
    val date = Date()
    val calendar: Calendar = Calendar.getInstance();
    calendar.time = date
    val today = if (calendar.get(Calendar.YEAR) == 2018) 1 else 0
    println("today=$today") // today=1
    val success = try {
        readFileWithException(filepath)
        true
    } catch (e: IOException) {
        e.printStackTrace()
        false
    }
    println("success=$success") // success=true

    // ======================================================================= null syntax
//    val nullstr : String = null // compile failed
    val str: String? = null
    println(str) // null
    // type checking
    fun isString(any: Any): Int { // use nested function
        return if (any is String) 1 else 0
    }
    println(isString(1)) // 0
    println(isString("hello")) // 1
    // smart casting
    fun printStringLength(any: Any) {
        if (any is String) {
            println(any.length) // just use String's method
        }
    }
    printStringLength("hello") // 5

    fun isEmpty(any: Any): Boolean {
        return any is String && any.length == 0 // smart cast in the right hand
    }
    println(isEmpty(1)) // false

    fun isNotStringOrEmpty(any: Any): Boolean {
        return any !is String || any.length == 0
    }
    println(isNotStringOrEmpty(1)) // true
    // explicit cast
    fun length(any: Any): Int? {
        val str: String? = any as String
        return str?.length
    }
    try {
        println(length(1))
    } catch (e: ClassCastException) {
        println(e.message)
    }
    println(length("hello")) // 5

    // ======================================================================= when expression
    // when(value)
    fun whatNumber(x: Int) {
        when (x) {
            0 -> println("x is zero")
            1 -> println("x is 1")
            else -> println("x is neither 0 or 1")
        }
    }
    whatNumber(123) // x is neither 0 or 1
    fun isZeroOrOne(x: Int): Boolean {
        return when (x) {
            0, 1 -> true
            else -> false
        }
    }
    println(isZeroOrOne(0)) // true
    println(isZeroOrOne(1)) // true
    println(isZeroOrOne(2)) // false
    fun isAbs(x: Int): Boolean {
        return when (x) {
            Math.abs(x) -> true
            else -> false
        }
    }
    println(isAbs(-1)) // false
    fun isSingleDigit(x: Int): Boolean {
        return when (x) {
            in -9..9 -> true // in range
            else -> false
        }
    }
    println(isSingleDigit(2)) // true

    fun isDieNumber(x: Int): Boolean {
        return when (x) {
            in listOf(1, 2, 3, 4, 5, 6) -> true // in list
            else -> false
        }
    }
    println(isDieNumber(2)) // true

    fun isStartWithFoo(any: Any): Boolean {
        return when (any) {
            is String -> any.startsWith("foo") // type check
            else -> false
        }
    }
    println(isStartWithFoo("foobar")) // true
    // when without argument: a replacement for if..else
    fun whenWithoutArgs(x: Int, y: Int) {
        when {
            x < y -> println("x is less than y")
            x > y -> println("x is greater than y")
            else -> println("x equals to y")
        }
    }
    whenWithoutArgs(2, 3) // x is less than y
    // function return with label
    fun printUntilStop() {
        val list = listOf("a", "b", "stop", "c")
        list.forEach { e ->
            // or use the special variable it
            if (e == "stop") return@forEach // skip print "stop"
            else println(e)
        }
    }
    printUntilStop() // a \n b \n c


    // ======================================================================= special type: Any, Unit, Nothing
    println(Any())
    println(emptyList<String>() as List<Nothing>)
    println(Unit)
}