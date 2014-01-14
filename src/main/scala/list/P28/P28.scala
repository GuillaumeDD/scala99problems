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
package list.P28

import util.ExerciseTemplate

trait P28 extends ExerciseTemplate {
  /*
    P28 (**) Sorting a list of lists according to length of sublists.
    a) We suppose that a list contains elements that are lists themselves. 
    The objective is to sort the elements of the list according to their length. 
    E.g. short lists first, longer lists later, or vice versa.

    Example:

    scala> lsort(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o)))
    res0: List[List[Symbol]] = List(List('o), List('d, 'e), List('d, 'e), List('m, 'n), List('a, 'b, 'c), List('f, 'g, 'h), List('i, 'j, 'k, 'l))

    b) Again, we suppose that a list contains elements that are lists themselves. 
    But this time the objective is to sort the elements according to their length frequency; 
    i.e. in the default, sorting is done ascendingly, 
    lists with rare lengths are placed, others with a more frequent length come later.

    Example:

    scala> lsortFreq(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o)))
    res1: List[List[Symbol]] = List(List('i, 'j, 'k, 'l), List('o), List('a, 'b, 'c), List('f, 'g, 'h), List('d, 'e), List('d, 'e), List('m, 'n))

    Note that in the above example, the first two lists in the result have length 4 and 1 and both lengths appear just once. The third and fourth lists have length 3 and there are two list of this length. Finally, the last three lists have length 2. This is the most frequent length.
 */
  val name = "P28 (Sorting a list of lists according to length of sublists)"
  def lsort[T](l: List[List[T]]): List[List[T]]
  def lsortFreq[T](l: List[List[T]]): List[List[T]]

  test("Invoking lsort on an empty list should return the empty list") {
    assert(lsort(List()) == List())
  }

  test("Invoking lsort on a list should sort the list according to its element size") {
    assert(lsort(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o))) ==
      List(List('o), List('d, 'e), List('d, 'e), List('m, 'n), List('a, 'b, 'c), List('f, 'g, 'h), List('i, 'j, 'k, 'l)))
  }

  test("Invoking lsortFreq on an empty list should return the empty list") {
    assert(lsortFreq(List()) == List())
  }

  test("Invoking lsortFreq on a list should sort the list accordingly to the length frequency") {
    assert(lsortFreq(List()) == List())
  }
}
