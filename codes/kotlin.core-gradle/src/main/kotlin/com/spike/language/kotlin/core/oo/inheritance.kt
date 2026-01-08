/**
 * Demonstration of inheritance.
 */
package com.spike.language.kotlin.core.oo

import java.io.InputStream
import java.math.BigDecimal
import java.sql.Driver
import java.util.*

enum class CardType {
    VISA, MASTERCARD, AMEX
}

open class Payment(val amount: BigDecimal) // non-final
class CardPayment(amount: BigDecimal, val number: String,
                  val expiryDate: Date, val type: CardType) : Payment(amount)

class ChequePayment : Payment {
    // call super(...)
    constructor(amount: BigDecimal, name: String, bankId: String) : super(amount) {
        this.name = name
        this.bankId = bankId
    }

    var name: String
        get() = this.name
    var bankId: String
        get() = this.bankId
}

// ======================================================================= implement multiple interfaces
interface Drivable {
    fun drive()
}

interface Sailable {
    fun saill()
}

class AmphibiousCar(val name: String) : Drivable, Sailable {
    override fun drive() {
        println("Driveing...")
    }

    override fun saill() {
        println("Sailling...")
    }
}

// ======================================================================= implement interface and extend class
interface IPersistable {
    fun save(stream: InputStream)
}

interface IPrintable {
    fun print()
}

abstract class AbsDocument(val title: String)

// the order of extended interfaces and class do not matter
class TextDocument(title: String) : IPersistable, AbsDocument(title), IPrintable {
    override fun save(stream: InputStream) {
        println("Saving to input stream")
    }

    override fun print() {
        println("Document name: $title")
    }
}


























