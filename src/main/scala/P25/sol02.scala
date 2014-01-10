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

import scala.reflect.ClassTag
import scala.collection.mutable.ArrayBuffer

object sol02 extends P25 {

  /**
   * Shuffle a list (Fisher-Yates shuffle algorithm)
   * @return Returns a random permutation of a list
   */
  def randomPermute[T](l: List[T]): List[T] =
    {
      /*
     * A word on the mutable structure.
     * This implementation uses ArrayBuffer due to intricacies 
     * in conversion of List[T] into Array[T] (which requires a ClassTag)
     * Following operations of ArrayBuffer are constant time complexity :
     * head, apply, update
     * See http://www.scala-lang.org/docu/files/collections-api/collections_40.html
     */
      val t = ArrayBuffer(l: _*)
      val n = t.size
      for (i <- n - 1 to 1 by -1) {
        // Selection of a random index between 1 and i
        val j = util.randomInt(i + 1)
        // Exchange t[j] and t[i]
        val temp = t(j)
        t.update(j, t(i))
        t.update(i, temp)
      }
      t.toList
    }
}
