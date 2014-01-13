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

class sol01 extends P27 {
  import list.P26.sol01._
  def group3[T](l: List[T]): List[List[List[T]]] = {
    require(l.size == 9)
    for {
      pair <- combinations(2, l)
      rest1 = l diff pair
      triple <- combinations(3, rest1)
      quadruple = rest1 diff triple
    } yield (List(pair, triple, quadruple))
  }

  def group[T](repartition: List[Int], l: List[T]): List[List[List[T]]] = {
    require(repartition.sum == l.size)
    repartition match {
      case List() => List(List())
      case head :: tail =>
        combinations(head, l).flatMap {
          solutionWithSizeHead =>
            group(tail, l diff solutionWithSizeHead).map(solutionWithSizeHead :: _)
        }
    }
  }

}
