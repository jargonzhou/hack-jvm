package com.spike.kotlin.c12_annotation_reflection.jkid

import com.spike.kotlin.json.JSONBaseVisitor
import com.spike.kotlin.json.JSONLexer
import com.spike.kotlin.json.JSONParser
import com.spike.kotlin.json.JSONParser.*
import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.DefaultErrorStrategy
import org.antlr.v4.runtime.tree.ParseTreeProperty
import kotlin.reflect.typeOf

class Parser(json: String, val rootObject: JsonObject) {
    fun parse() {
        TODO("Not yet implemented")
    }
}

data class JsonContextHolder(val rootObject: Seed, val newName: String)

class JsonVisitor(val rootObject: Seed) : JSONBaseVisitor<JsonObject>() {
    private val scopes = ParseTreeProperty<String>()


    override fun visitJson(ctx: JsonContext?): JsonObject? {
        return visitValue(ctx?.value())
    }

    override fun visitObj(ctx: ObjContext?): JsonObject? {
        return visitChildren(ctx)
    }


    // 1
    override fun visitPair(ctx: PairContext?): JsonObject? {
        val name = ctx?.STRING()?.text ?: ""
        scopes.put(ctx, name)
        this.visitValue(ctx?.value())
        return rootObject
    }

    override fun visitArr(ctx: ArrContext?): JsonObject? {
        return visitChildren(ctx)
    }

    // 2
    override fun visitValue(ctx: ValueContext?): JsonObject? {
        val name  = scopes.get(ctx!!.parent)
        if (ctx.STRING() != null) {
            // FIXME
//            return rootObject.setSimpleProperty()
        }
        if (ctx.NUMBER() != null) {
            return rootObject.createSeedForType(typeOf<Number>(), false)
        }
        if (ctx.getChild(4) != null) {
            rootObject.setSimpleProperty(name, true)
        }
        if (ctx.getChild(5) != null) {
            rootObject.setSimpleProperty(name, false)
        }
        if (ctx.getChild(6) != null) {
            rootObject.setSimpleProperty(name, null)
        }
        return visitChildren(ctx)
    }
}

class JsonErrorListener : BaseErrorListener()

val JsonErrorStrategy = DefaultErrorStrategy()


// =============================================================================
// tests
// =============================================================================

data class Author(val name: String)
data class Book(val title: String, val author: Author)

fun main() {
    val input = """{"title":"Catch-22","author":{"name":"J.Heller"}}"""
    val charStream = CharStreams.fromString(input)
    val jsonLexer = JSONLexer(charStream)
    val tokenStream = CommonTokenStream(jsonLexer)
    val jsonParser = JSONParser(tokenStream)
    jsonParser.errorHandler = JsonErrorStrategy
    jsonParser.addErrorListener(JsonErrorListener())

    val jsonVisitor = JsonVisitor(ObjectSeed(Book::class, ClassInfoCache()))
    jsonVisitor.visitJson(jsonParser.json())
}