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
package arithmetic.P41

import util.ExerciseTemplate
import arithmetic.P40.sol01.goldbach
object P41 extends App {
  /*
 	P41 (**) A list of Goldbach compositions.
    Given a range of integers by its lower and upper limit, print a list of all even numbers and their Goldbach composition.

    scala> printGoldbachList(9 to 20)
    10 = 3 + 7
    12 = 5 + 7
    14 = 3 + 11
    16 = 3 + 13
    18 = 5 + 13
    20 = 3 + 17

    In most cases, if an even number is written as the sum of two prime numbers, one of them is very small. Very rarely, the primes are both bigger than, say, 50. Try to find out how many such cases there are in the range 2..3000.

    Example (minimum value of 50 for the primes):

    scala> printGoldbachListLimited(1 to 2000, 50)
    992 = 73 + 919
    1382 = 61 + 1321
    1856 = 67 + 1789
    1928 = 61 + 1867
 */
  def printGoldbachList(range: Range): Unit = {
    for {
      n <- range
      if n % 2 == 0
    } {
      val (a, b) = goldbach(n)
      println(s"$n = $a + $b")
    }
  }

  def printGoldbachListLimited(range: Range, minLimit: Int): Unit = {
    for {
      n <- range
      if n % 2 == 0
    } {
      val (a, b) = goldbach(n)
      if (a >= minLimit && b >= minLimit)
        println(s"$n = $a + $b")
    }
  }

  printGoldbachList(9 to 20)
  println("----")
  printGoldbachListLimited(3 to 2000, 50)
}
