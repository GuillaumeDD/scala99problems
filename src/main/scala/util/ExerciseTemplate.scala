/**
 * *****************************************************************************
 * Copyright (c) 2013 Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors:
 *     Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr> - initial API and implementation
 * ****************************************************************************
 */
package util

import org.scalatest.FunSuite

trait ExerciseTemplate extends FunSuite {

  /**
   * @return Name of the exercise (number, description)
   */
  val name: String

  def main(args: Array[String]) {
    println("Running exercice " + name)
    execute(color = false)
  }
}
