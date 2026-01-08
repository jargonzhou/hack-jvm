package com.spike.language.kotlin.core.oo

import java.net.URI
import java.util.*

/**
 * Demonstration of data class
 *
 * @author zhoujiagen
 */

data class BlogEntry(
        var title: String,
        var description: String,
        val publishTime: Date,
        val approved: Boolean?,
        val lastUpdated: Date,
        val url: URI,
        val commentCount: Int?,
        val topTags: List<String>,
        val email: String?
)

fun main(args: Array<String>) {
    val now = Date()
    val blogEntry = BlogEntry("Data Classes are here", "Because Kotlin rulz!", now, true, now,
            URI("http://packt.com/blog/programming_kotlin/data_classes"), 0,
            emptyList(), null)

    // toString
    println(blogEntry)

    // getter/setter
    println(blogEntry.title)
    blogEntry.title = "Data Classes are here now"
    println(blogEntry.title)

    // equals, hashCode
    println(blogEntry.equals(blogEntry))
    println(blogEntry.hashCode())

    // copy
    val newBlogEntry = blogEntry.copy(title = "Data Classes are here now!")
    println(newBlogEntry)

    // destructed declaration
    val (title, description, publishTime, approved, lastUpdated, url,
            comments, tags, email) = blogEntry
    println("title=$title, description=${description}...")
}