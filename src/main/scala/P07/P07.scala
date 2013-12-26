/*******************************************************************************
 * Copyright (c) 2013 Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr> - initial API and implementation
 ******************************************************************************/
package P07

import util.ExerciseTemplate

trait P07 extends ExerciseTemplate {
  /*
	P07 (**) Flatten a nested list structure.
    Example:

    scala> flatten(List(List(1, 1), 2, List(3, List(5, 8))))
    res0: List[Any] = List(1, 1, 2, 3, 5, 8)
  */
  val name = "P07 (Flatten a nested list structure)"
  def flatten(l: List[Any]): List[Any]

  test("Invoking flatten on an empty list should return an empty list") {
    assert(flatten(List()) == List())
  }

  test("Invoking flatten should flatten a list") {
    assert(flatten(List(List(1, 1), 2, List(3, List(5, 8)))) == List(1, 1, 2, 3, 5, 8))
    assert(flatten(List('a, 'b, 'c, 'd)) == List('a, 'b, 'c, 'd))
  }
}
