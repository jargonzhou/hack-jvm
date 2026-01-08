/**
 * Demonstration of OO in Kotlin.
 */
package com.spike.language.kotlin.core.oo

import java.awt.Button
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent


class Person(val firstName: String, val lastName: String, val age: Int?) {
    // primary constructor code
    init {
        // throw IllegalArgumentException if check failed
        require(firstName.trim().length > 0) { "Invalid firstName argument!" }
        require(lastName.trim().length > 0) { "Invalid lastName argument!" }
        if (age != null) {
            require(age in 0..150) { "Invalid age argment!" }
        }
    }

    // secondary constructor: should call the primary constructor
    // val, var is not valid here
    constructor(firstName: String, lastName: String) : this(firstName, lastName, null)
}

// no var/val in constructor parameter
class Person2(firstName: String, lastName: String, ageInput: Int?) {
    private val name: String
    private val age: Int?

    init {
        this.name = "$firstName, $lastName"
        this.age = ageInput
    }

    fun getName(): String = this.name
    fun getAge(): Int? = this.age
}

class Connection
class Database internal constructor(val connection: Connection)


// ======================================================================= nested class
class OuterClassName {
    // static nested class: can only access the public members
    class InnerClassName
}

class BasicGraph(val name: String) {
    class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
        fun draw(): Unit {
            println("Drawing line from ($x1, $y1) to ($x2, $y2)")
        }
    }

    fun draw(): Unit {
        println("Drawing the graph $name")
    }
}

class BasicGraphWithInner(graphName: String) {
    private val name: String

    init {
        name = graphName
    }

    inner class InnerLine(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
        fun draw(): Unit {
            println("Drawing line from ($x1, $y1) to ($x2, $y2) for graph $name") // access 'name'
        }
    }

    fun draw(): Unit {
        println("Drawing the graph $name")
    }
}

// ======================================================================= anonymous class
class Controller {
    private var clicked: Int = 0

    fun enableHook(button: Button) {
        button.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                clicked++ // can access outer non-final field!!!
            }
        })
    }
}

// ======================================================================= data class
data class Customer(val id: Int, val name: String, val address: String)

// ======================================================================= enum class
enum class DayEnum {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
    SATURDAY, SUNDAY
}

// enum class with constructor, default property: name, ordinal
enum class Planet(val mass: Double, val radius: Double) {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.37814e6),
    MARS(6.421e+23, 3.3972e6),
    JUPITER(1.9e+27, 7.1492e7),
    SATURN(5.688e+26, 6.0268e7),
    URANUS(8.686e+25, 2.5559e7),
    NEPTUNE(1.024e+26, 2.4746e7);
}

// enum class can implement interface
interface Printable {
    fun print(): Unit
}

enum class Word : Printable {
    HELLO {
        override fun print() {
            println("Word is HELLO")
        }
    },
    BYE {
        override fun print() {
            println("Word is BYE")
        }
    }
}


// ======================================================================= companion object
// static method: fun at package level
fun showFirstCharacter(input: String): Char {
    if (input.isEmpty()) throw IllegalArgumentException()
    return input.first()
}

// singleton: object
object SINGLETON {
    private var count: Int = 0
    fun doSomething(): Unit {
        println("Calling a doSomething (${++count} call/-s in  total)")
    }
}

// singleton derived from a class
open class SingletonParent(var x: Int) {
    fun something(): Unit {
        println("x=$x")
    }
}

object SingletonDerive : SingletonParent(10) {}

// companion object
interface StudentFactory {

}


// ======================================================================= main
fun main(args: Array<String>) {
    val persons = listOf(Person("Alex", "Smith", 2),
            Person("Jane", "Smith"))
    for (person in persons) {
        println("${person.firstName} ${person.lastName}, ${person.age ?: "?"}") // ?:
    }

    // inner class
    // static inner class
    val basicGraph = BasicGraph("BasicGraph")
    val line = BasicGraph.Line(0, 0, 1, 1) // OuterClass.InnerClass
    line.draw()
    basicGraph.draw()
    // non-static inner class
    val basicGraphWithInner = BasicGraphWithInner("BasicGraphWithInner")
    val innerLine = basicGraphWithInner.InnerLine(0, 0, 1, 1) // OuterClassInstance.InnerClass
    innerLine.draw()
    basicGraphWithInner.draw()

    // Can we create class in method like Java?: Yes!
    class A {
        private val somefield: Int = 1

        inner class B {
            private val somefield: Int = 1
            fun foo() {
                println(this.somefield) // B.somefield
                println(this@B.somefield) // B.somefield
                println(this@A.somefield) // A.somefield
            }
        }
    }


}