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
package multiwayTree.P70

import util.ExerciseTemplate
import multiwayTree.MTree

trait P70 extends ExerciseTemplate {
  /*
    P70 (**) Tree construction from a node string.
    We suppose that the nodes of a multiway tree contain single characters. 
    In the depth-first order sequence of its nodes, a special character ^ has been inserted whenever, 
    during the tree traversal, the move is a backtrack to the previous level.

    By this rule, the tree in the figure opposite is represented as:

    afg^^c^bd^e^^^

    Define the syntax of the string and write a function string2MTree to construct an MTree from a String. 
    Make the function an implicit conversion from String. 
    Write the reverse function, and make it the toString method of MTree.

    scala> MTree('a', List(MTree('f', List(MTree('g'))), MTree('c'), MTree('b', List(MTree('d'), MTree('e'))))).toString
    res0: String = afg^^c^bd^e^^^ 
  */
  val name = "P70 (Tree construction from a node string)"

  def string2MTree(strTree: String): MTree[Char]
  def MTree2String(t: MTree[Char]): String

  test("Invoking string2MTree should create a tree from a string") {
    assert(string2MTree("a") == MTree('a'))
    assert(string2MTree("abc^^") == MTree('a', List(MTree('b', List(MTree('c'))))))
    assert(string2MTree("afg^^c^bd^e^^") == MTree('a', List(MTree('f', List(MTree('g'))), MTree('c'), MTree('b', List(MTree('d'), MTree('e'))))))
  }

  test("Invoking MTree2string should create a string from a tree") {
    assert(MTree2String(MTree('a')) == "a")
    assert(MTree2String(MTree('a', List(MTree('b', List(MTree('c')))))) == "abc^^")
    assert(MTree2String(MTree('a', List(MTree('f', List(MTree('g'))), MTree('c'), MTree('b', List(MTree('d'), MTree('e')))))) == "afg^^c^bd^e^^")
  }
}
