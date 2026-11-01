package com.spike.kotlin.c05_lambdas


// Java functional interface: SAM(Single Abstract Methods)

fun SAM.handleComputation(id: String) {
    postponeComputation(1000) {
        println(id)
    }
}

fun createAllDoneRunnable(): Runnable {
    // SAM constructor
    return Runnable { println("All done!") }
}

fun main() {
    val button = Button("a")
    // pass a lambda
    button.setOnClickListener { view -> println(view) }

    val sam = SAM()
    sam.postponeComputation(1000) { println(42) }
    // pass an object expression
    sam.postponeComputation(1000, object : Runnable {
        override fun run() {
            println(42)
        }
    })

    val button1 = Button("1")
    val button2 = Button("2")
    // SAM constructor
    val listener = OnClickListener { view ->
        val text = when (view.id) {
            button1.id -> "First button"
            button2.id -> "Second button"
            else -> "Unknown button"
        }
        println(text)
    }
    button1.setOnClickListener(listener)
    button2.setOnClickListener(listener)
}