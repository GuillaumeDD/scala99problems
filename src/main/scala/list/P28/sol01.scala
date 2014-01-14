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
package list.P28

import scala.collection.immutable.List

class sol01 extends P28 {

  def lsort[T](l: List[List[T]]): List[List[T]] =
    l.sortBy(_.size)

  def lsortFreq[T](l: List[List[T]]): List[List[T]] =
    {
      // Creation of the frequency map from the list l
      val frequencyMap =
        l.map(_.size)
          .groupBy(x => x)
          .mapValues(_.size)
          .withDefaultValue(0)
      l.sortBy(ll => frequencyMap(ll.size))
    }
}
