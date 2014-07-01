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
package binaryTree.P69

import util.ExerciseTemplate
import binaryTree.Tree
import binaryTree.End
import binaryTree.Node

trait P69 extends ExerciseTemplate {
  /*
	P69 (**) Dotstring representation of binary trees.
    We consider again binary trees with nodes that are identified by single lower-case letters, as in the example of problem P67. 
    Such a tree can be represented by the preorder sequence of its nodes in which dots (.) are inserted where an empty subtree (End) is encountered during the tree traversal. For example, the tree shown in problem P67 is represented as "abd..e..c.fg...". First, try to establish a syntax (BNF or syntax diagrams) and then write two methods, toDotstring and fromDotstring, which do the conversion in both directions.

    scala> Tree.string2Tree("a(b(d,e),c(,f(g,)))").toDotstring
    res0: String = abd..e..c.fg...

    scala> Tree.fromDotstring("abd..e..c.fg...")
    res1: Node[Char] = a(b(d,e),c(,f(g,)))
   */
  val name = "Dotstring representation of binary trees (P69)"
  def toDotstring(t: Tree[Char]): String
  def fromDotstring(s: String): Tree[Char]

  test("Invoking toDotstring should return the String representation") {
    assert(toDotstring(End) == ".")
    assert(toDotstring(Node('a')) == "a..")
    assert(toDotstring(Node('a', End, Node('b'))) == "a.b..")
    assert(toDotstring(Node('a', Node('b'), End)) == "ab...")
    val tree = toDotstring(Node('a', Node('b', Node('d'), Node('e')), Node('c', End, Node('f', Node('g'), End))))
    assert(tree == "abd..e..c.fg...")
  }

  test("Invoking fromDotstring should return the tree from a string representation") {
    assert(fromDotstring(".") == End)
    assert(fromDotstring("a..") == Node('a'))
    assert(fromDotstring("a.b..") == Node('a', End, Node('b')))
    assert(fromDotstring("ab...") == Node('a', Node('b'), End))
    assert(fromDotstring("abd..e..c.fg...") == Node('a', Node('b', Node('d'), Node('e')), Node('c', End, Node('f', Node('g'), End))))
  }
}
