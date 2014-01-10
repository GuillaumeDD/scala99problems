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
package P25

import util.ExerciseTemplate

trait P25 extends ExerciseTemplate {
  /*
    P25 (*) Generate a random permutation of the elements of a list.
    Hint: Use the solution of problem P23.

    Example:

    scala> randomPermute(List('a, 'b, 'c, 'd, 'e, 'f))
    res0: List[Symbol] = List('b, 'a, 'd, 'c, 'e, 'f)
    */
  val name = "P25 (Generate a random permutation of the elements of a list)"
  def randomPermute[T](l: List[T]): List[T]

  test("Invoking randomPermute should return a random permutation of the elements of a list") {
    val l = List(1, 2, 3, 4, 5)
    val randomPermutation = randomPermute(l)
    assert(l.size == randomPermutation.size)
    assert(l.forall(elt => randomPermutation.contains(elt)))
  }

  test("Invoking randomPermute on an empty list should return an empty list") {
    assert(randomPermute(List()) == List())
  }
}
