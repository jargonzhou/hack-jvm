/**
 * Demonstration of sealed class in Kotlin:
 *
 * 1. parent class is an abstract class;
 * 2. child class defined as inner class (static) in parent class.
 *
 */
package com.spike.language.kotlin.core.oo

// data is not compatible with sealed
sealed class IntBinaryTree {
    abstract fun print(): String

    class EmptyNode : IntBinaryTree() {
        override fun print(): String = "_"
    }

    class IntBinaryTreeNode(val left: IntBinaryTree, val right: IntBinaryTree, val value: Int) : IntBinaryTree() {
        override fun print(): String {
            return """[${left.print()}
                |$value
                |${right.print()}]""".trimMargin()
        }
    }
}

// the 'when' expression must be exhaustive
fun toCollect(tree: IntBinaryTree): List<Int> = when (tree) {
    is IntBinaryTree.EmptyNode -> emptyList()
    is IntBinaryTree.IntBinaryTreeNode -> toCollect(tree.left) + tree.value + toCollect(tree.right)
}

fun main(args: Array<String>) {
    val tree = IntBinaryTree.IntBinaryTreeNode(
            IntBinaryTree.IntBinaryTreeNode(
                    IntBinaryTree.EmptyNode(),
                    IntBinaryTree.EmptyNode(),
                    1),
            IntBinaryTree.EmptyNode(),
            10)
    println(tree.print())
    println(toCollect(tree).joinToString(", "))
}