package com.spike.language.kotlin.core.fp

import jdk.nashorn.internal.ir.RuntimeNode
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

// ======================================================================= single expression function
fun hello(): String = "Hello"

fun hello(name: String, location: String): String {
    return "hello to you $name at $location"
}

fun concat1(a: String, b: String) = a + b
fun concat2(a: String, b: String): String {
    return a + b
}

// ======================================================================= member function
object Rectange {
    fun printArea(width: Int, height: Int): Unit {
        println("the area is ${calculateArea(width, height)}")
    }

    fun calculateArea(width: Int, height: Int): Int {
        return width * height;
    }
}

// ======================================================================= local function

fun printArea(width: Int, height: Int): Unit {
    // local function
    fun calculateArea(width: Int, height: Int): Int = width * height

    fun calculateArea(): Int = width * height // reference outer scope variables

    val area = calculateArea(width, height)
    val area2 = calculateArea()
    println("the area is $area, $area2")
}

// local function in loop
fun fizzbuzz(start: Int, end: Int): Unit {
    for (k in start..end) {
        fun isFizz(): Boolean = k % 3 == 0
        fun isBizz(): Boolean = k % 5 == 0

        when {
            isFizz() -> println("Fizz")
            isBizz() -> println("Bizz")
            else -> println(k)
        }
    }
}

// ======================================================================= top level function
// function defined in a file: 'require'
fun validate(k: Int) {
    require(k > 0) { "k should greater than zero" }
}

// ======================================================================= named parameter & default parameter
fun deleteFiles(filePattern: String,
                recursive: Boolean,
                ignoreCase: Boolean,
                deleteDirectories: Boolean): Unit {
    println("parameters: $filePattern, $recursive, $ignoreCase, $deleteDirectories")
}

// default parameter
fun createThreadPool(threadCount: Int = Runtime.getRuntime().availableProcessors()): ExecutorService {
    return Executors.newFixedThreadPool(threadCount)
}



// ======================================================================= vargs



// =======================================================================
// =======================================================================
// =======================================================================
// =======================================================================
// =======================================================================


fun main(args: Array<String>) {
    // named parameter
    // mix named and positional parameter is *not* allowed
    deleteFiles("", true, false, false)
    deleteFiles(filePattern = "", ignoreCase = false, recursive = true, deleteDirectories = false)
    // once use a named parameter, the following should also be named
    deleteFiles("", ignoreCase = false, recursive = true, deleteDirectories = false)

    // default parameter
    //createThreadPool()
    //createThreadPool(100)
}