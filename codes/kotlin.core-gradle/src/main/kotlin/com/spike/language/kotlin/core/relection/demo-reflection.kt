package com.spike.language.kotlin.core.relection

import kotlin.reflect.KClass

fun main(args: Array<String>) {
    val name: String = "Cartman"
    val kclass: KClass<out String> = name::class
    println(kclass)

    val kclass2 = Class.forName("java.lang.String").kotlin
    println(kclass2)
}




