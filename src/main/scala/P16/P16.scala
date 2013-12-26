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
package P16

import util.ExerciseTemplate

trait P16 extends ExerciseTemplate {
  /*
	P16 (**) Drop every Nth element from a list.
    Example:

    scala> drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
    res0: List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
	*/
  val name = "P16 (Drop every Nth element from a list)"
  def drop[T](freq: Int, l: List[T]): List[T]

  test("Invoking drop on an empty list should return an empty list") {
    assert(drop(3, List()) == List())
  }

  test("Invoking drop on a list should return a list") {
    assert(drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k))
  }

  test("Invoking drop with frequence 1 should return an empty list") {
    assert(drop(1, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == List())
  }
}
