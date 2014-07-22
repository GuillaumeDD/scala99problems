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
package multiwayTree.P71

import multiwayTree.MTree

class sol01 extends P71 {

  def internalPathLength[T](t: MTree[T]): Int =
    t.children.foldLeft(0)(
      (pathLength, child) => pathLength +
        child.nodeCount +
        internalPathLength(child))
}
