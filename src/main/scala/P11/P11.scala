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
package P11

import util.ExerciseTemplate

trait P11 extends ExerciseTemplate {
  /*
	P11 (*) Modified run-length encoding.
    Modify the result of problem P10 in such a way that if an element has no duplicates it is simply copied into the result list. Only elements with duplicates are transferred as (N, E) terms.

    Example:

    scala> encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    res0: List[Any] = List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e))
	*/
  val name = "P11 (Modified run-length encoding)"
  def encodeModified[T](l: List[T]): List[Either[T, (Int, T)]]

  test("Invoking encodeModified on an empty list should return an empty list") {
    assert(encodeModified(List()) == List())
  }

  test("Invoking encodeModified on a list should return the encoded list") {
    assert(encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) == List[Either[Symbol, (Int, Symbol)]](Right((4, 'a)), Left('b), Right((2, 'c)), Right((2, 'a)), Left('d), Right((4, 'e))))
  }
}
