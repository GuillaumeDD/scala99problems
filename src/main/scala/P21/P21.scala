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
package P21

import util.ExerciseTemplate

trait P21 extends ExerciseTemplate {
  /*
	P21 (*) Insert an element at a given position into a list.
    Example:

    scala> insertAt('new, 1, List('a, 'b, 'c, 'd))
    res0: List[Symbol] = List('a, 'new, 'b, 'c, 'd)
	*/
  val name = "P21 (Insert an element at a given position into a list)"
  def insertAt[T](elt: T, index: Int, l: List[T]): List[T]

  test("Invoking insertAt on list should insert an element into the list") {
    assert(insertAt('new, 0, List('a, 'b, 'c, 'd)) == List('new, 'a, 'b, 'c, 'd))
    assert(insertAt('new, 1, List('a, 'b, 'c, 'd)) == List('a, 'new, 'b, 'c, 'd))
    assert(insertAt('new, 2, List('a, 'b, 'c, 'd)) == List('a, 'b, 'new, 'c, 'd))
    assert(insertAt('new, 3, List('a, 'b, 'c, 'd)) == List('a, 'b, 'c, 'new, 'd))
    assert(insertAt('new, 4, List('a, 'b, 'c, 'd)) == List('a, 'b, 'c, 'd, 'new))
    assert(insertAt('new, 42, List('a, 'b, 'c, 'd)) == List('a, 'b, 'c, 'd, 'new))

    assert(insertAt('new, 1, List()) == List('new))
  }
}
