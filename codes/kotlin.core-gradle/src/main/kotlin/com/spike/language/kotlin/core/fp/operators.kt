/**
 * Demonstration of operator overriding supports in Kotlin: english alias name.
 */
package com.spike.language.kotlin.core.fp

import java.util.*

// ======================================================================= operator
// +, add
data class Matrix(val a: Int, val b: Int, val c: Int, val d: Int) {
    operator fun plus(matrix: Matrix): Matrix { // alias of '+'
        return Matrix(a + matrix.a,
                b + matrix.b,
                c + matrix.c,
                d + matrix.d)
    }
}

// in, contains
fun in_and_containes() {
    // keyword 'in' can be override by 'contains'
    val ints = listOf(1, 2, 3)
    val a = 3 in ints
    val b = ints.contains(3)
    assert(a === b)
}

// array[index], array[index]=value, get/set
enum class Piece {
    Empty, Pawn, Bishop, Knight, Rook, Queen, King
}

class ChessBoard() {
    private val board = Array<Piece>(64, { Piece.Empty })

    operator fun get(rank: Int, file: Int): Piece = board[file * 8 + rank]
    operator fun set(rank: Int, file: Int, value: Piece): Unit {
        board[file * 8 + rank] = value
    }
}

// (), invoke: invoke object
class RandomLongs(seed: Long) {
    private val random = Random(seed)
    operator fun invoke(): Long = random.nextLong()
    // can overload with parameters
    operator fun invoke(times: Int): Long = random.nextLong() * times
}

// comparison: compareTo
class BingoNumber(val name: String, val age: Int) {
    operator fun compareTo(other: BingoNumber): Int {
        return when {
            age < other.age -> -1
            age > other.age -> 1
            else -> 0
        }
    }
}

// assignment: +=
class Counter(val k: Int) {
    operator fun plus(j: Int): Counter = Counter(k + j)
}

// Java interops
// public Matrix plus(Matrix other) { }

fun main(args: Array<String>) {
    val matrix1 = Matrix(1, 2, 3, 4)
    val matrix2 = Matrix(2, 3, 4, 5)
    println("$matrix1, $matrix2")
    val matrix3 = matrix1 + matrix2
    println(matrix3)

    val intList = listOf(1, 2, 3, 4)
    println(1 in intList)
    println(intList.contains(5))

    in_and_containes()
    val board = ChessBoard()
    board[0, 4] = Piece.Queen
    println(board[0, 4])

    val randomLongs = RandomLongs(1L)
    println(randomLongs())
    println(randomLongs(2))

    val a = BingoNumber("Key to the Door", 21)
    val b = BingoNumber("Jump and Jive", 35)
    println(a > b) // false
    println(a < b) // true
    println(b > a) // true

    var counter = Counter(1)
    counter = counter + 3
    println(counter.k) // 4
    counter += 2
    println(counter.k) // 6

}

