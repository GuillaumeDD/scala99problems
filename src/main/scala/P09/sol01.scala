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
package P09

object sol01 extends P09 {
  def pack[T](l: List[T]): List[List[T]] =
    // Départ de la fin de la liste
    (l foldRight List(List[T]()))((head, llist) =>
      if (llist.head.isEmpty || llist.head.head == head) {
        // Ajout de l'élément en tête de liste
        (head +: llist.head) +: llist.tail
      } else {
        (head +: List[T]()) +: llist
      })
}
