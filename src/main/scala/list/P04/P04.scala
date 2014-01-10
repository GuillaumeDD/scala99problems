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
package list.P04

import util.ExerciseTemplate

trait P04 extends ExerciseTemplate {
  /*
	P04 (*) Find the number of elements of a list.
    Example:

    scala> length(List(1, 1, 2, 3, 5, 8))
    res0: Int = 6
	*/
  val name = "P04 (Find the number of elements of a list)"
  def length[T](l: List[T]): Int
  
  test("Invoking length on a list should return its length") {
    assert(length(List()) == 0)
    assert(length(List(2,1)) == 2)
    assert(length(List(1, 1, 2, 3, 5, 8)) == 6)
  }
}
