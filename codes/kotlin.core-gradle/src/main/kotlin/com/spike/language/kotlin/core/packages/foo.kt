package com.spike.language.kotlin.core.packages

import java.util.*
import java.lang.String as JavaString // renaming

class Foo

fun bar(): String = "bar"

fun main(args: Array<String>) {
    Foo()
    bar()

    // for import case
    ArrayList<Integer>()
    JavaString.valueOf(123)
}