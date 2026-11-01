package com.spike.kotlin.c13_dsl

// =============================================================================
// Lambdas with receivers and extension function types
// =============================================================================

// StringBuilder.(): the receiver type - extension function type
fun myBuildString(builderAction: StringBuilder.() -> Unit): String {
    val sb = StringBuilder()
    builderAction(sb)
    return sb.toString()
}

// extension function type
// String.(Int, Int) -> Unit
fun String.substringInclusive(start: Int, end: Int): String {
    return this.substring(start, end + 1)
}

// a variable of extension function type
val appendExcl: StringBuilder.() -> Unit = {
    this.append("!")
}

fun main() {
    val s = myBuildString {
        append("Hello, ")
        append("Kotlin!")
    }
    println(s) // Hello, Kotlin!

    val sb = StringBuilder("Hi")
    sb.appendExcl()
    println(sb) // Hi!
    println(myBuildString(appendExcl)) // !


    val map = mutableMapOf(1 to "one")
    map.apply { this[2] = "two" }
    with(map) { this[3] = "three" }
    println(map) // {1=one, 2=two, 3=three}

    println(createTable()) // <table><tr><td></td></tr></table>
    println(createTable2()) // <table id="trid"><tr id="trid"><td></td></tr></table>
    println(createAnotherTable()) // <table><tr><td></td></tr><tr><td></td></tr></table>
}


//inline fun <T> T.apply(block: T.() -> Unit): T {
//    block()
//    return this
//}

//inline fun <T, R> with(receiver: T, block: T.() -> R): R {
//    return receiver.block()
//}

// =============================================================================
// Using lambdas with receivers in HTML builders
// =============================================================================
// kotlinx.html

@DslMarker // <--- constrains the availability of outer receivers in lambdas
annotation class HtmlTagMarker

@HtmlTagMarker
open class Tag(val name: String) {
    private val children = mutableListOf<Tag>()
    var id: String? = null

    protected fun <T : Tag> doInit(child: T, init: T.() -> Unit) {
        child.init()
        children.add(child)
    }


    override fun toString(): String {
        val idStr = if (id.isNullOrEmpty()) "" else " id=\"$id\""
        return "<$name$idStr>${children.joinToString("")}</$name>"
    }
}

class TD : Tag("td")
class TR : Tag("tr") {
    // construct td in tr
    fun td(init: TD.() -> Unit) = doInit(TD(), init)
}

class TABLE : Tag("table") {
    var tableId: String? = null

    // construct tr in table
    fun tr(init: TR.() -> Unit) = doInit(TR(), init)

}

fun table(init: TABLE.() -> Unit) = TABLE().apply(init)

fun createTable() = table {
    tr {
        td {}
    }
}

fun createTable2() = table {
    // this@table: get the receiver
    this@table.tr {
        this@table // can also access

        id = "trid" // access property in Tag
        // can NOT access due to @HtmlTagMarker
        // tableId = "trid" // access property in TABLE
        this@table.id = "trid"

        this@tr.td {
            // can access all
            this@table
            this@tr
            this@td
        }
    }
}

fun createAnotherTable() = table {
    for (i in 1..2) {
        tr {
            td {}
        }
    }
}

// =============================================================================
// Kotlin builders: Enabling abstraction and reuse
// =============================================================================