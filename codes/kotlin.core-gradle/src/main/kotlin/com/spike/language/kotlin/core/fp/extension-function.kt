/**
 * Demonstration of extension function in Kotlin to surround class inheritance limit.
 */
package com.spike.language.kotlin.core.fp

import java.math.BigDecimal
import java.util.*

// ======================================================================= extension is shadowed by member

class Submarine {
    fun fire(): Unit {
        println("Fire torpedos")
    }

    fun submerge(): Unit {
        println("Submerge")
    }
}

// extension is shadowed by member
fun Submarine.fire(): Unit {
    println("Fire torpedos")
}

fun Submarine.submerge(depth: Int): Unit {
    println("Submerge: $depth")
}

// ======================================================================= extension functions on nulls
fun Any?.safeEquals(other: Any?): Boolean {
    if (this == null && other == null) return true
    if (this == null) return false
    return this == other
}

// ======================================================================= member extension function
class Mappings {
    private val map = hashMapOf<Int, String>()
    private val mappingMap = hashMapOf<Int, String>()
    private fun String.stringAdd(): Unit {
        //map.put(this.hashCode(), this)
        map[this.hashCode()] = this
        mappingMap[this@Mappings.hashCode()] = this
        println("DEBUG: $map, $mappingMap")
    }

    fun add(str: String) = str.stringAdd()
}

// ======================================================================= overriding member extension function
open class Particle

class Electron : Particle()

open class Element(val name: String) {
    open fun Particle.react(name: String): Unit {
        println("$name is reacting with a particle")
    }

    open fun Electron.react(name: String): Unit {
        println("$name is reacting with an electron to make an  isotope")
    }

    fun react(particle: Particle): Unit { // Particle
        particle.react(name)
    }
}

class NobleGas(name: String) : Element(name) {
    // override
    override fun Particle.react(name: String): Unit {
        println("$name is noble, it doesn't react with particles")
    }

    // override
    override fun Electron.react(name: String): Unit {
        println("$name is noble, it doesn't react with electrons")
    }

    fun react(particle: Electron): Unit { // Electron
        particle.react(name)
    }
}

// ======================================================================= companion object extention function
fun Int.Companion.random() = Random().nextInt()


// ======================================================================= multiple return values: pair, triple

// ======================================================================= infix function

class Account(var balance: BigDecimal) {
    // infix function
    infix fun add(amount: BigDecimal): Unit {
        this.balance = balance.add(amount)
    }
}


fun main(args: Array<String>) {
    val alist = listOf<Int>(1, 2, 3)
    // see /kotlin-stdlib-common-1.2.41-sources.jar!/generated/_Collections.kt:264
    // public expect fun <T> Iterable<T>.drop(n: Int): List<T>
    println(alist.drop(2).joinToString(", "))


    val submarine = Submarine()
    submarine.fire() // Fire torpedos
    submarine.submerge() // Submerge
    submarine.submerge(2) // Submerge: 2

    Mappings().add("123")

    val selenium = Element("Selenium")
    // com.spike.language.kotlin.core.fp.Element.react(com.spike.language.kotlin.core.fp.Particle)
    selenium.react(Particle()) // Selenium is reacting with a particle
    selenium.react(Electron()) // Selenium is reacting with a particle
    val neon = NobleGas("Neon")
    // com.spike.language.kotlin.core.fp.Element.react(com.spike.language.kotlin.core.fp.Particle) & overrided
    neon.react(Particle()) // Neon is noble, it doesn't react with particles
    // com.spike.language.kotlin.core.fp.NobleGas.react(com.spike.language.kotlin.core.fp.Electron)
    neon.react(Electron()) // Neon is noble, it doesn't react with electrons


    println(Int.random())

    val pair = Pair(1, "a")
    val (p1, p2) = pair // destruction
    println("$pair, $p1, $p2")
    val triple = Triple(1, "a", 1.0)
    val (t1, t2, t3) = triple
    println("$triple, $t1, $t2, $t3")


    val account = Account(BigDecimal(123))
    account add BigDecimal(1) // infix
    println(account.balance)
}