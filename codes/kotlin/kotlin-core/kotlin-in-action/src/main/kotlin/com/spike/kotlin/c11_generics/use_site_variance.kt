package com.spike.kotlin.c11_generics

fun <T> copyData(
    source: MutableList<out T>, // producer
    destination: MutableList<in T> // consumer
) {
    for (item in source) {
        destination.add(item)
    }
}