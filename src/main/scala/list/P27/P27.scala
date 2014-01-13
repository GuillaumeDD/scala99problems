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
package list.P27

import util.ExerciseTemplate

trait P27 extends ExerciseTemplate {
  /*
    P27 (**) Group the elements of a set into disjoint subsets.
    a) In how many ways can a group of 9 people work in 3 disjoint subgroups of 2, 3 and 4 persons? 
    Write a function that generates all the possibilities.

    Example:

    scala> group3(List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida"))
    res0: List[List[List[String]]] = List(List(List(Aldo, Beat), List(Carla, David, Evi), List(Flip, Gary, Hugo, Ida)), ...

    b) Generalize the above predicate in a way that we can specify a list of group sizes and the predicate will 
    return a list of groups.

    Example:

    scala> group(List(2, 2, 5), List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida"))
    res0: List[List[List[String]]] = List(List(List(Aldo, Beat), List(Carla, David), List(Evi, Flip, Gary, Hugo, Ida)), ...

    Note that we do not want permutations of the group members; i.e. ((Aldo, Beat), ...) is the same solution as ((Beat, Aldo), ...). 
    However, we make a difference between ((Aldo, Beat), (Carla, David), ...) and ((Carla, David), (Aldo, Beat), ...).

    You may find more about this combinatorial problem in a good book on discrete mathematics under the term "multinomial coefficients".
 */
  val name = "P27 (Group the elements of a set into disjoint subsets)"
  def group3[T](l: List[T]): List[List[List[T]]]
  def group[T](repartition: List[Int], l: List[T]): List[List[List[T]]]

  test("Invoking group3 on a list of size 9") {
    val result =
      group3(List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida"))
    assert(result.size == 1260) // 9!/(2!*3!*4!)
  }

  test("Invoking group on a list of size 9 with repartition (2, 2, 5)") {
    val result =
      group(List(2, 2, 5), List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida"))
    assert(result.size == 756) // 9!/(2!*2!*5!)
  }
}
