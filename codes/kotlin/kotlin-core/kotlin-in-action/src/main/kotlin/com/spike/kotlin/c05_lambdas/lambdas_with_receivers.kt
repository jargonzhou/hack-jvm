package com.spike.kotlin.c05_lambdas

// =============================================================================
// with: perform multiple operations on the same object
// =============================================================================
fun alphabet(): String {
    val sb = StringBuilder()
    // receiver: sb
    return with(sb) {
        for (letter in 'A'..'Z') {
            this.append(letter)
        }
        this.append("\nNow I know the alphabet!")

        this.toString()
    }
    // omit this
//    return with(sb) {
//        for (letter in 'A'..'Z') {
//            append(letter)
//        }
//        append("\nNow I know the alphabet!")
//
//        toString()
//    }
}

fun alphabet2() = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")

    toString()
}

class OuterClass {
    fun alphabet() = with(StringBuilder()) {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nNow I know the alphabet!")

        // refer to OuterClass#toString
        this@OuterClass.toString()
    }
}

// =============================================================================
// apply: initialize and configure objects
// always return the object passed to it as an argument(the receiver object)
// =============================================================================
fun alphabet3() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}.toString()

// builderAction: StringBuilder.() -> Unit
// StringBuilder.(): the receiver type
val s = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}

val fibonacci = buildList {
    addAll(listOf(1, 1, 2))
    add(3)
    add(index = 0, element = 3)
}

val shouldAdd = true
val fruits = buildSet {
    add("Apple")
    if (shouldAdd) {
        addAll(listOf("Apple", "Banana", "Cherry"))
    }
}

val medals = buildMap<String, Int> {
    put("Gold", 1)
    putAll(listOf("Silver" to 2, "Bronze" to 3))
}

// =============================================================================
// also: perform additional actions with an object
// =============================================================================

fun main() {
    val theFruits = listOf("Apple", "Banana", "Cherry")
    val uppercaseFruits = mutableListOf<String>()
    val reversedLongFruits = theFruits
        .map { it.uppercase() }
        .also { uppercaseFruits.addAll(it) }
        .filter { it.length > 5 }
        .also(::println) // [BANANA, CHERRY]
        .reversed()
    println(uppercaseFruits) // [APPLE, BANANA, CHERRY]
    println(reversedLongFruits) // [CHERRY, BANANA]
}


















