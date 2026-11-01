package com.spike.kotlin.c11_generics

// the type alias
typealias NameCombiner = (String, String, String, String) -> String

val authorCombiner: NameCombiner = { a, b, c, d -> "$a et al." }
val bandCombiner: NameCombiner = { a, b, c, d -> "$a, $b & The Gang" }

fun combineAuthors(combiner: NameCombiner) {
    println(combiner("Sveta", "Seb", "Dima", "Roman"))
}

// type aliases v.s. inline classes
//typealias ValidatedInput = String
//
//fun save(v: ValidatedInput): Unit = TODO()

@JvmInline
value class ValidatedInput2(val value: String)

fun save2(v: ValidatedInput2): Unit {
    return
}

fun main() {
    combineAuthors(bandCombiner) // Sveta, Seb & The Gang
    combineAuthors(authorCombiner) // Sveta et al.
    combineAuthors { a, b, c, d -> "$d, $c & Co." } // Roman, Dima & Co.

    val rawInput = "needs validating"
//    save(rawInput)
//    ERROR: Argument type mismatch: actual type is 'String', but 'ValidatedInput2' was expected.
//    save2(rawInput)
    save2(ValidatedInput2(rawInput))
}