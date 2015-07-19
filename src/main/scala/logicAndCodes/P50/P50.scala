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
package logicAndCodes.P50

import util.ExerciseTemplate

trait P50 extends ExerciseTemplate {
  /*
	P50 (***) Huffman code.
    First of all, consult a good book on discrete mathematics or algorithms for a detailed description of Huffman codes!

    We suppose a set of symbols with their frequencies, given as a list of (S, F) Tuples. E.g. (("a", 45), ("b", 13), ("c", 12), ("d", 16), ("e", 9), ("f", 5)). Our objective is to construct a list of (S, C) Tuples, where C is the Huffman code word for the symbol S.

    scala> huffman(List(("a", 45), ("b", 13), ("c", 12), ("d", 16), ("e", 9), ("f", 5)))
    res0: List[String, String] = List((a,0), (b,101), (c,100), (d,111), (e,1101), (f,1100))
	 */
  val name = "P50 (Huffman code)"
  def huffman[T](frequencies: List[(T, Int)]): List[(T, String)]

  test("Invoking Huffman on an empty list of symbols should return an empty list") {
    assert(huffman(List()) == List())
  }

  test("Invoking Huffman on a list of symbols and their frequencies should return their Huffman code") {
    val solution01 = List(("a", "0"), ("b", "101"), ("c", "100"), ("d", "111"), ("e", "1101"), ("f", "1100"))
    val attempt01 = huffman(List(("a", 45), ("b", 13), ("c", 12), ("d", 16), ("e", 9), ("f", 5))).sortBy(_._1)
    assert(attempt01.size == solution01.size)
    assert(solution01.forall(attempt01.contains(_)), s"$attempt01 does not equal $solution01")

    val solution02 = List(("a", "0"), ("b", "11"), ("c", "101"), ("d", "100"))
    val attempt02 = huffman(List(("a", 400), ("b", 350), ("c", 200), ("d", 5))).sortBy(_._1)
    assert(attempt02.size == solution02.size)
    assert(solution02.forall(attempt02.contains(_)), s"$attempt02 does not equal $solution02")
  }
}
