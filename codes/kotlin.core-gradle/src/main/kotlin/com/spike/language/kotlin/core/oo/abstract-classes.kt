package com.spike.language.kotlin.core.oo

import java.util.*

abstract class A {
    abstract fun doSomething()
}

open class AParent protected constructor() {
    open fun someMethod(): Int = Random().nextInt()
}

abstract class DDerived : AParent() {
    // change to abstract method
    abstract override fun someMethod(): Int
}

class AlwaysOne : DDerived() {
    override fun someMethod(): Int {
        return 1
    }
}