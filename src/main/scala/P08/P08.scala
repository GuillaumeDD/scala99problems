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
package P08

import util.ExerciseTemplate

trait P08 extends ExerciseTemplate {
  /*
	P08 (**) Eliminate consecutive duplicates of list elements.
    If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed.

    Example:

    scala> compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    res0: List[Symbol] = List('a, 'b, 'c, 'a, 'd, 'e)
	*/
  val name = "P08 (Eliminate consecutive duplicates of list elements)"
  def compress[T](l: List[T]): List[T]

  test("Invoking compress on an empty list return the empty list") {
    assert(compress(List()) == List())
  }

  test("Invoking compress on a list shoud compress the list") {
    assert(compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) == List('a, 'b, 'c, 'a, 'd, 'e))
  }
}
