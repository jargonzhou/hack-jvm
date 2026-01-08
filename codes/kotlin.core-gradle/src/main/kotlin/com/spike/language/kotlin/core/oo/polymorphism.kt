package com.spike.language.kotlin.core.oo


// ERROR: StackOverflowError
// REF: https://www.callicoder.com/kotlin-properties-backing-fields-getters-setters/
//abstract class Shape protected constructor() {
//    var XLocation: Int
//        get() = this.XLocation
//        set(value: Int) {
//            this.XLocation = value
//        }
//    var YLocation: Int
//        get() = this.XLocation
//        set(value: Int) {
//            this.XLocation = value
//        }
//    var Width: Double
//        get() = this.Width
//        set(value: Double) {
//            this.Width = value
//        }
//    var Height: Double
//        get() = this.Height
//        set(value: Double) { // the set cuase StackOverflowError
//            this.Height = value
//        }
//    abstract fun isHit(x: Int, y: Int): Boolean
//}


abstract class Shape protected constructor() {
    var XLocation: Int = 0
        get() = field
        set(value: Int) {
            field = value
        }
    var YLocation: Int = 0
        get() = field
        set(value: Int) {
            field = value
        }
    var Width: Double = 0.0
        get() = field
        set(value: Double) {
            field = value
        }
    var Height: Double = 0.0
        get() = field
        set(value: Double) {
            field = value
        }

    abstract fun isHit(x: Int, y: Int): Boolean
}

class Ellipsis : Shape() {
    override fun isHit(x: Int, y: Int): Boolean {
        val xRadius = Width / 2
        val yRadius = Height / 2
        val centerX = XLocation + xRadius
        val centerY = YLocation + yRadius
        if (xRadius == 0.0 || yRadius == 0.0)
            return false
        val normalizedX = centerX - XLocation
        val normalizedY = centerY - YLocation
        return (normalizedX * normalizedX) / (xRadius * xRadius) +
                (normalizedY * normalizedY) / (yRadius * yRadius) <= 1.0
    }
}

class Rectangle : Shape() {
    override fun isHit(x: Int, y: Int): Boolean {
        return x >= XLocation && x <= (XLocation + Width)
                && y >= YLocation && y <= (YLocation + Height)
    }
}

fun main(args: Array<String>) {
    val e1 = Ellipsis()
    e1.Height = 10.toDouble()
    e1.Width = 12.toDouble()

    val e2 = Ellipsis()
    e2.XLocation = 100
    e2.YLocation = 96
    e2.Height = 21.toDouble()
    e2.Width = 19.toDouble()

    val r1 = Rectangle()
    r1.XLocation = 49
    r1.YLocation = 45
    r1.Width = 10.toDouble()
    r1.Height = 10.toDouble()

    val shapes: List<Shape> = listOf<Shape>(e1, e2, r1)

    val selected: Shape? = shapes.firstOrNull { shape ->
        shape.isHit(50, 52)
    }

    if (selected == null) {
        println("There is no shape at point(50,52)")
    } else {
        println("A shape of type ${selected.javaClass.simpleName} has  been selected .")
    }
}