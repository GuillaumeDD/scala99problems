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
package binaryTree.P68

import util.ExerciseTemplate
import binaryTree.Tree
import binaryTree.Node
import binaryTree.End

trait P68 extends ExerciseTemplate {
  /*
    P68 (**) Preorder and inorder sequences of binary trees.
    We consider binary trees with nodes that are identified by single lower-case letters, as in the example of problem P67.

    a) Write methods preorder and inorder that construct the preorder and inorder sequence of a given binary tree, respectively. 
    The results should be lists, e.g. List('a','b','d','e','c','f','g') for the preorder sequence of the example in problem P67.

    scala> Tree.string2Tree("a(b(d,e),c(,f(g,)))").preorder
    res0: List[Char] = List(a, b, d, e, c, f, g)

    scala> Tree.string2Tree("a(b(d,e),c(,f(g,)))").inorder
    res1: List[Char] = List(d, b, e, a, c, g, f)

    b) If both the preorder sequence and the inorder sequence of the nodes of a binary tree are given, then the tree is determined unambiguously. 
    Write a method preInTree that does the job.

    scala> Tree.preInTree(List('a', 'b', 'd', 'e', 'c', 'f', 'g'), List('d', 'b', 'e', 'a', 'c', 'g', 'f'))
    res2: Node[Char] = a(b(d,e),c(,f(g,)))

    What happens if the same character appears in more than one node? Try, for instance, Tree.preInTree(List('a', 'b', 'a'), List('b', 'a', 'a')).
 */
  val name = "Preorder and inorder sequences of binary trees (P68)"
  def preorder[T](t: Tree[T]): List[T]
  def inorder[T](t: Tree[T]): List[T]
  def preInTree(preorder: List[Char], inorder: List[Char]): Tree[Char]

  test("Invoking preorder and inorder on an End should return an empty list") {
    assert(preorder(End) == List())
    assert(inorder(End) == List())
  }

  test("Invoking preorder and inorder on a Tree[Char] should return the preorder and inorder sequence of a tree") {
    val solution = Node('a', Node('b', Node('d'), Node('e')), Node('c', End, Node('f', Node('g'), End)))
    assert(preorder(solution) == List('a', 'b', 'd', 'e', 'c', 'f', 'g'))
    assert(inorder(solution) == List('d', 'b', 'e', 'a', 'c', 'g', 'f'))
  }

  test("Invoking preInTree should return the unambiguous tree") {
    val solution = Node('a', Node('b', Node('d'), Node('e')), Node('c', End, Node('f', Node('g'), End)))
    assert(preInTree(List('a', 'b', 'd', 'e', 'c', 'f', 'g'), List('d', 'b', 'e', 'a', 'c', 'g', 'f')) == solution)
  }
}
