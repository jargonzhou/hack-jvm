package com.spike.kotlin.c13_dsl

import kotlinx.html.*
import kotlinx.html.HtmlTagMarker
import kotlinx.html.stream.createHTML

// to remove duplicates
fun buildBookList() = createHTML().body {
    ul {
        li { a("#1") { +"The Three-Body Problem" } }
        li { a("#2") { +"The Dark Forest" } }
        li { a("#3") { +"Death's End" } }
    }

    h2 { id = "1"; +"The Three-Body Problem" }
    p { +"The first book tackles..." }

    h2 { id = "2"; +"The Dark Forest" }
    p { +"The second book starts with..." }

    h2 { id = "3"; +"Death's End" }
    p { +"The third book contains..." }
}

fun buildBookList2() = createHTML().body {
    listWithToc {
        item("The Three-Body Problem", "The first book tackles...")
        // ...
    }
}

@HtmlTagMarker
class LISTWITHTOC {
    val entries = mutableListOf<Pair<String, String>>()
    fun item(headline: String, body: String) {
        entries += headline to body
    }
}

fun BODY.listWithToc(block: LISTWITHTOC.() -> Unit) {
    val listWithToc = LISTWITHTOC()
    listWithToc.block()
    ul {
        for ((index, entry) in listWithToc.entries.withIndex()) {
            li { a("#$index") { +entry.first } }
        }
    }
    for ((index, entry) in listWithToc.entries.withIndex()) {
        h2 { id = "$index"; +entry.first }
        p { +entry.second }
    }
}

fun main() {
    println(buildBookList())
    println(buildBookList2())
}

//<body>
//  <ul>
//    <li><a href="#1">The Three-Body Problem</a></li>
//    <li><a href="#2">The Dark Forest</a></li>
//    <li><a href="#3">Death's End</a></li>
//  </ul>
//  <h2 id="1">The Three-Body Problem</h2>
//  <p>The first book tackles...</p>
//  <h2 id="2">The Dark Forest</h2>
//  <p>The second book starts with...</p>
//  <h2 id="3">Death's End</h2>
//  <p>The third book contains...</p>
//</body>

//<body>
//  <ul>
//    <li><a href="#0">The Three-Body Problem</a></li>
//  </ul>
//  <h2 id="0">The Three-Body Problem</h2>
//  <p>The first book tackles...</p>
//</body>
