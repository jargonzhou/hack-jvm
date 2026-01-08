/**
 * Demonstration of class delegation in Kotlin.
 */
package com.spike.language.kotlin.core.oo

interface DelegationUIElement {
    fun getHeight(): Int
    fun getWidth(): Int
}

class DelegationRectangle(val x1: Int, val x2: Int, val y1: Int, val y2: Int) : DelegationUIElement {
    override fun getHeight(): Int {
        println("DelegationRectangle:getHeight")
        return (y2 - y1)
    }

    override fun getWidth(): Int {
        println("DelegationRectangle:getWidth")
        return (x2 - x1)
    }
}

// delegation: composition and 'by'
class Panel(val rectangle: DelegationRectangle) : DelegationUIElement by rectangle


fun main(args: Array<String>) {
    val panel = Panel(DelegationRectangle(10, 100, 30, 100))
    println("Panel height:" + panel.getHeight())
    println("Panel witdh:" + panel.getWidth())

}