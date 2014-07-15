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
package multiwayTree.P70

import multiwayTree.MTree
import org.scalatest.Args

class sol01 extends P70 {
  def string2MTree(strTree: String): MTree[Char] = {
    /**
     * Computes the next position in string strTree that is at the same
     * level as pos.
     *
     */
    def nextSameDepthPosition(pos: Int, depth: Int): Int = {
      require(pos <= strTree.length)
      if (depth == 0 || pos >= strTree.length)
        pos
      else {
        val nextDepth =
          if (strTree(pos) == '^')
            depth - 1
          else
            depth + 1
        nextSameDepthPosition(pos + 1, nextDepth)
      }
    }

    def splitChildStrings(pos: Int): List[String] =
      {
        if (pos >= strTree.length) {
          List()
        } else {
          val nextPosition = nextSameDepthPosition(pos + 1, 1)
          strTree.substring(pos, nextPosition - 1) :: splitChildStrings(nextPosition)
        }
      }

    MTree(strTree(0), splitChildStrings(1).map(string2MTree(_)))
  }

  def MTree2String(t: MTree[Char]): String =
    t.value + t.children.map(child => MTree2String(child) + "^").mkString("")
}
