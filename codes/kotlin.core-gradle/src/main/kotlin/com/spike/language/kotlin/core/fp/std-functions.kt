/**
 * Demonstration of standard library functions:
 *
 * apply, let, with, run, lazy, use, repeat, require/assert/check.
 */
package com.spike.language.kotlin.core.fp

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {

// ======================================================================= apply
    val task = Runnable { println("running") }
    // 'apply' return the receiver
    Thread(task).apply { isDaemon = true }.start()

// ======================================================================= let
    // like 'apply' except return the value of closure
    val valueOfLet = "abc".let { it.length }
    println(valueOfLet) // 3

// ======================================================================= with
    // call multiple functions with one object, not repeatedly writing the object
    val list = listOf(1, 2, 3)
    with(list) {
        println(list.size)
        for (e in list) {
            println(e)
        }
    }

// ======================================================================= run
    // run = with + let
    val outputPath = Paths.get("/tmp").run {
        val path = resolve("output")
        path.toFile().createNewFile()
        println("${path.toAbsolutePath()} exists? ${path.toFile().exists()}")
        path
    }

    // ======================================================================= lazy
    // wrap expensive function call, when first required then invoke
    fun readStringFromDatabase(): String {
        Thread.sleep(1000L)
        println("invoked!!!") // show only once
        return "hello"
    }

    val lazyString = lazy { readStringFromDatabase() }
    val string = lazyString.value // first requires
    println("$string, $string")

// ======================================================================= use
    // like Java 7's try-with-resources
    val input = Files.newInputStream(Paths.get("/tmp/output"))
    val byte = input.use { input.read() }
    println(byte)

// ======================================================================= repeat
    // repeat function literal n times
    repeat(2, { println("hello") })

    // ======================================================================= require/assert/check
    fun neverEmpty(string: String) {
        require(string.length > 0, { "String should not be empty" })
    }

    fun neverNegative(int: Int) {
        assert(int >= 0, { "int shoud be positive or zero" })
    }

    fun neverPositive(int: Int) {
        check(int <= 0, { "int shoud be negative or zero" })
    }

    try {
        neverEmpty("")
    } catch (e: Exception) {
        // class java.lang.IllegalArgumentException, String should not be empty
        println("${e::class}: ${e.message}")
    }

    // JVM assert option, vm arguments: -enableassertions or -ea
    try {
        neverNegative(-1)
    } catch (e: AssertionError) {
        // class java.lang.AssertionError: int shoud be positive or zero
        println("${e::class}: ${e.message}")
    }

    try {
        neverPositive(1)
    } catch (e: Exception) {
        // class java.lang.IllegalStateException: int shoud be negative or zero
        println("${e::class}: ${e.message}")
    }
}