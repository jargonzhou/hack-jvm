package com.spike.kotlin.c05_lambdas

// lambda expressions and member references

data class Person(val name: String, val age: Int)

// extension function
fun Person.isAdult() = age >= 21

fun salute() = println("Salute!")

fun sendEmail(person: Person, message: String) {
}

fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.maxBy { it.age }) // selector
    println(people.maxBy(Person::age)) // member reference

    val sum = { x: Int, y: Int ->
        println("Computing the sum of $x and $y")
        x + y // result: the last expression
    }
    println(sum(1, 2));
    { println(42) }()

    run { println(42) }

    val myFavoriteNumber = run {
        println("I'm thinking!")
        println("I'm doing some more work...")
        42
    }

    println(people.joinToString(" ") { it.name }) // Alice Bob

    // printMessagesWithPrefix
    // printProblemCounts

    // member reference
    val getAge = Person::age
    val getAge2 = { person: Person -> person.age }

    // reference to top-level function
    run(::salute) // Salute!

    val action = { person: Person, message: String ->
        sendEmail(person, message)
    }
    val nextAction = ::sendEmail

    // reference to constructor
    val createPerson = ::Person
    val p = createPerson("Alice", 29)
    println(p) // Person(name=Alice, age=29)

    // reference to extension function
    val predicate = Person::isAdult
    println(predicate(p)) // true

    // bound callable references
    val seb = Person("Sebastian", 26)
    val personsAgeFunction = Person::age
    println(personsAgeFunction(seb)) // 26
    val sebsAgeFunction = seb::age
    println(sebsAgeFunction()) // 26
}

fun printMessagesWithPrefix(messages: Collection<String>, prefix: String) {
    messages.forEach {
        println("$prefix $it")
    }
}

fun printProblemCounts(responses: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0
    responses.forEach {
        if (it.startsWith("4")) {
            clientErrors++ // modify variables
        } else if (it.startsWith("5")) {
            serverErrors++
        }
    }
    println("$clientErrors client errors, $serverErrors server errors")
}