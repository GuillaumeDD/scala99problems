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
package arithmetic.P36
import util.tictac

object BenchmarkP36 extends App {
  // Loading primes
  val primes = arithmetic.Util.primes
  // Benchmark
  tictac("P36-sol01") {
    (new sol01()).primeFactorMultiplicity(315)
  }

  tictac("P36-sol02") {
    (new sol02()).primeFactorMultiplicity(315)
  }

  tictac("P36-sol03") {
    (new sol03()).primeFactorMultiplicity(315)
  }
}
