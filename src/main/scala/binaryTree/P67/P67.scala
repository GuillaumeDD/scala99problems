/**
 * *****************************************************************************
 * Copyright (c) 2014 Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors:
 *     Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr> - initial API and implementation
 * ****************************************************************************
 */
package binaryTree.P67

import util.ExerciseTemplate
import binaryTree.Tree
import binaryTree.End
import binaryTree.Node

trait P67 extends ExerciseTemplate {
  /*
    P67 (**) A string representation of binary trees.
    Somebody represents binary trees as strings of the following type (see example opposite):

    a(b(d,e),c(,f(g,)))

    Write a method which generates this string representation, if the tree is given as usual (in Nodes and Ends). 
    Use that method for the Tree class's and subclass's toString methods. 
    Then write a method (on the Tree object) which does this inverse; i.e. given the string representation, construct the tree in the usual form.

    For simplicity, suppose the information in the nodes is a single letter and there are no spaces in the string.

    scala> Node('a', Node('b', Node('d'), Node('e')), Node('c', End, Node('f', Node('g'), End))).toString
    res0: String = a(b(d,e),c(,f(g,)))

    scala> Tree.fromString("a(b(d,e),c(,f(g,)))")
    res1: Node[Char] = a(b(d,e),c(,f(g,)))
  */
  val name = "A string representation of binary trees (P67)"
  def toString[T](t: Tree[T]): String
  def fromString(str: String): Tree[Char]

  test("Invoking toString on an End should return an empty String") {
    assert(toString(End) == "")
  }

  test("Invoking toString on a Node should return the String representation of the node") {
    assert(toString(Node('a')) == "a")
    assert(toString(Node('a', End, Node('b'))) == "a(,b)")
    assert(toString(Node('a', Node('b'), End)) == "a(b,)")
    assert(toString(Node('a', Node('b'), Node('c'))) == "a(b,c)")
  }

  test("Invoking toString on a Tree should return the String representation of the tree") {
    val result = toString(Node('a', Node('b', Node('d'), Node('e')), Node('c', End, Node('f', Node('g'), End))))
    val solution = "a(b(d,e),c(,f(g,)))"
    assert(result == solution)
  }

  test("Invoking fromString on an empty string should return an End") {
    assert(fromString("") == End)
  }

  test("Invoking fromString on a node representation should return a Node") {
    assert(fromString("a") == Node('a'))
    assert(fromString("a(b,c)") == Node('a', Node('b'), Node('c')))
    assert(fromString("a(b,)") == Node('a', Node('b'), End))
    assert(fromString("a(,b)") == Node('a', End, Node('b')))
  }

  test("Invoking fromString on a Tree representation should return the tree") {
    val solution = Node('a', Node('b', Node('d'), Node('e')), Node('c', End, Node('f', Node('g'), End)))
    val result = fromString("a(b(d,e),c(,f(g,)))")
    assert(result == solution)
  }
}
