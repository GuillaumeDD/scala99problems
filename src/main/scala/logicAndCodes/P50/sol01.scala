/*******************************************************************************
 * Copyright (c) 2015 Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr> - initial API and implementation
 ******************************************************************************/
package logicAndCodes.P50

import scala.collection.immutable.List
import scala.collection.mutable

class sol01 extends P50 {
  // Huffman tree
  def fuse[T](n1: Node[T], n2: Node[T]): Node[T] =
    if (n1.weight <= n2.weight) {
      NonLeafNode(n1.weight + n2.weight, n1, n2)
    } else {
      NonLeafNode(n1.weight + n2.weight, n2, n1)
    }

  sealed abstract class Node[T] {
    def weight: Int
    def decode(): List[(T, String)]
    def decodeHelper(codePrefix: String): List[(T, String)]
  }
  case class NonLeafNode[T](weight: Int, left: Node[T], right: Node[T]) extends Node[T] {
    def decode(): List[(T, String)] =
      decodeHelper("")

    def decodeHelper(codePrefix: String): List[(T, String)] =
      left.decodeHelper(codePrefix + "0") ++ right.decodeHelper(codePrefix + "1")
  }
  case class Leaf[T](weight: Int, content: T) extends Node[T] {
    def decode(): List[(T, String)] =
      decodeHelper("")

    def decodeHelper(codePrefix: String): List[(T, String)] =
      List((content, codePrefix))
  }

  // Solution
  def huffman[T](frequencies: List[(T, Int)]): List[(T, String)] = {
    def buildTree[T](priorityQueue: mutable.PriorityQueue[Node[T]]): Node[T] = {
      require(priorityQueue.size > 0, s"Empty priority queue")
      if (priorityQueue.size == 1) {
        priorityQueue.dequeue
      } else {
        // While there is more than one node in the queue
        // Remove the two nodes of highest priority (lowest probability) from the queue
        val firstNode = priorityQueue.dequeue
        val secondNode = priorityQueue.dequeue

        // Create a new internal node with these two nodes as children and with probability 
        // equal to the sum of the two nodes' probabilities.
        val newNode = fuse(firstNode, secondNode)

        //Add the new node to the queue.
        priorityQueue.enqueue(newNode)
        buildTree(priorityQueue)
      }
    }

    frequencies match {
      case List() =>
        List()
      case nonEmptyList =>
        val nodes = frequencies.map({
          case (item, freq) => Leaf(freq, item)
        })
        val ordering = Ordering.by[Node[T], Int](_.weight).reverse
        val priorityQueue = mutable.PriorityQueue[Node[T]](nodes: _*)(ordering)
        val tree = buildTree(priorityQueue)
        tree.decode()
    }

  }
}
