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
package list.P26

import util.ExerciseTemplate

trait P26 extends ExerciseTemplate {
  /*
    P26 (**) Generate the combinations of K distinct objects chosen from the N elements of a list.
    In how many ways can a committee of 3 be chosen from a group of 12 people? We all know that there are C(12,3) = 220 possibilities (C(N,K) denotes the well-known binomial coefficient). For pure mathematicians, this result may be great. But we want to really generate all the possibilities.

    Example:

    scala> combinations(3, List('a, 'b, 'c, 'd, 'e, 'f))
    res0: List[List[Symbol]] = List(List('a, 'b, 'c), List('a, 'b, 'd), List('a, 'b, 'e), ...
 */
  val name = "P26 (Generate the combinations of K distinct objects chosen from the N elements of a list)"
  def combinations[T](size: Int, l: List[T]): List[List[T]]

  test("Invoking combinations on an empty list with size > 0 should return List()") {
    assert(combinations(42, List()) == List())
  }

  test("Invoking combinations on a list of length n with size > n should return List()") {
    assert(combinations(42, List(1, 2, 3, 4)) == List())
  }

  test("Invoking combinations on a list of length n with size = 0 should return List(List())") {
    assert(combinations(0, List()) == List(List()))
    assert(combinations(0, List(1, 2, 3, 4)) == List(List()))
    assert(combinations(0, List.fill(42)('a)) == List(List()))
  }

  test("Invoking combinations on a list of length n with size = n should return List(l') with l' having the same elements than the initial list") {
    val l = List(1, 2, 3, 4)
    val combinationsList = combinations(4, l)
    assert(combinationsList.size == 1)
    assert(combinationsList.head.forall(l.contains(_)))
  }

  test("Invoking combinations on a list should return its combinations") {
    val combinationList = combinations(2, List(1, 2, 3, 4))
    assert(combinationList.size == 6)
    assert(combinationList.forall(_.size == 2))
    assert(combinationList.contains(List(1, 2)) || combinationList.contains(List(2, 1)))
    assert(combinationList.contains(List(1, 3)) || combinationList.contains(List(3, 1)))
    assert(combinationList.contains(List(1, 4)) || combinationList.contains(List(4, 1)))
    assert(combinationList.contains(List(2, 3)) || combinationList.contains(List(3, 2)))
    assert(combinationList.contains(List(2, 4)) || combinationList.contains(List(4, 2)))
    assert(combinationList.contains(List(3, 4)) || combinationList.contains(List(4, 3)))

    val bigCombinationList = combinations(3, List.range(0, 12, 1))
    assert(bigCombinationList.size == 220)
    assert(bigCombinationList.forall(_.size == 3))
  }
}
