/*******************************************************************************
 * Copyright (c) 2014 Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr> - initial API and implementation
 ******************************************************************************/
package multiwayTree.P73

import util.ExerciseTemplate
import multiwayTree.MTree

trait P73 extends ExerciseTemplate {
  /*
    P73 (**) Lisp-like tree representation.
    There is a particular notation for multiway trees in Lisp. 
    Lisp is a prominent functional programming language. In Lisp almost everything is a list.

    Our example tree would be represented in Lisp as (a (f g) c (b d e)). The following pictures give some more examples.

    Note that in the "lispy" notation a node with successors (children) in the tree is always the first element in a list, 
    followed by its children. 
    The "lispy" representation of a multiway tree is a sequence of atoms and parentheses '(' and ')', 
    with the atoms separated by spaces. 
    We can represent this syntax as a Scala String. 
    Write a method lispyTree which constructs a "lispy string" from an MTree.

    scala> MTree("a", List(MTree("b", List(MTree("c"))))).lispyTree
    res0: String = (a (b c))

    As a second, even more interesting, exercise try to write a method that takes a "lispy" string and turns it into a 
    multiway tree.
 */
  val name = "P73 (Lisp-like tree representation)"
  def lipsy(t: MTree[Char]): String
  def lipsyStringToMTree(t: String): MTree[Char]

  test("Invoking lipsy should return the LISP-like string representation of a tree") {
    /*
     *    a
     */
    assert(lipsy(MTree('a')) == "a")

    /*
     *    a
     *    |
     *    b
     */
    assert(lipsy(MTree('a', List(MTree('b')))) == "(a b)")

    /*
     *    a
     *    |
     *    b
     *    |
     *    c
     */
    assert(lipsy(MTree('a', List(MTree('b', List(MTree('c')))))) == "(a (b c))")

    /*
     *     b
     *    / \
     *   d   e
     */
    assert(lipsy(MTree('b', List(MTree('d'), MTree('e')))) == "(b d e)")

    /*
     *        a
     *      / | \
     *     f  c  b
     *     |    / \
     *     g   d   e
     *
     */
    assert(lipsy(MTree('a', List(MTree('f', List(MTree('g'))), MTree('c'), MTree('b', List(MTree('d'), MTree('e')))))) ==
      "(a (f g) c (b d e))")
  }

  test("Invoking lipsyStringToMTree should return a tree from its LISP-like string representation") {
    /*
     *    a
     */
    assert(lipsyStringToMTree("a") == MTree('a'))

    /*
     *    a
     *    |
     *    b
     */
    assert(lipsyStringToMTree("(a b)") == MTree('a', List(MTree('b'))))

    /*
     *    a
     *    |
     *    b
     *    |
     *    c
     */
    assert(lipsyStringToMTree("(a (b c))") == MTree('a', List(MTree('b', List(MTree('c'))))))

    /*
     *     b
     *    / \
     *   d   e
     */
    assert(lipsyStringToMTree("(b d e)") == MTree('b', List(MTree('d'), MTree('e'))))

    /*
     *        a
     *      / | \
     *     f  c  b
     *     |    / \
     *     g   d   e
     *
     */
    assert(lipsyStringToMTree("(a (f g) c (b d e))") ==
      MTree('a', List(MTree('f', List(MTree('g'))), MTree('c'), MTree('b', List(MTree('d'), MTree('e'))))))
  }
}
