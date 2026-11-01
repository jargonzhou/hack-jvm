package com.spike.kotlin.c05_lambdas

// fun interface
fun interface IntCondition {
    fun check(i: Int): Boolean // exactly one abstract method
    fun checkString(s: String) = check(s.toInt())
    fun checkChar(c: Char) = check(c.digitToInt())
}

fun checkCondition(i: Int, condition: IntCondition): Boolean {
    return condition.check(i)
}

fun main() {
    val isOdd = IntCondition { it % 2 == 0 }
    println(isOdd.check(1)) // false
    println(isOdd.checkString("2")) // true
    println(isOdd.checkChar('3')) // false

    println(checkCondition(1) { it % 2 == 0 }) // false
    val isOdd2: (Int) -> Boolean = { it % 2 == 0 }
    println(checkCondition(1, isOdd2)) // false
}

fun interface StringConsumer {
    fun consume(s: String)
}

fun consumeHello(t: StringConsumer) {
    t.consume("Hello")
}

fun consumeHelloFunctional(t: (String) -> Unit) {
    t("Hello")
}