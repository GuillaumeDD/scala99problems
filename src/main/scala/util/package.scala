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
package object util {
  /**
   * Returns a pseudorandom, uniformly distributed int value between 0 (inclusive)
   * and the specified value (exclusive)
   * @param borneSup Specified value (excluded)
   */
  def randomInt(borneSup: Int): Int =
    scala.util.Random.nextInt(borneSup)

  // Modified from: http://stackoverflow.com/questions/9160001/how-to-profile-methods-in-scala
  def tictac[R](label: String, repetition: Int = 10000)(block: => R): Double = {
    require(repetition > 1)
    val computedTimes =
      for (i <- 1 to repetition)
        yield ({
        val t0 = System.nanoTime()
        val result = block // call-by-name
        val t1 = System.nanoTime()
        t1 - t0
      })

    // Drop the first computation of time (which is empirically often aberrant)
    val times = computedTimes.tail
    val sizeOfTimes = repetition - 1
    val avgTime = times.sum.toDouble / sizeOfTimes
    val standardDeviation = Math.sqrt(variance(times.map(_ / 1000), avgTime / 1000))
    // Uncomment to display the series of measure (sorted and associated with their index)
    // println(times.map(_ / 1000).zip(1 to sizeOfTimes).sortBy(x => x._1).mkString(", "))
    println(f"$label | Elapsed time: ${avgTime / 1000}%4.2fµs (standard deviation: $standardDeviation%4.2f)")
    avgTime
  }

  private def variance(seq: IndexedSeq[Long], avg: Double): Double =
    {
      require(seq.size > 1)
      seq.foldLeft(0.0)((acc, elt) => acc + Math.pow((avg - elt), 2)) / (seq.size - 1)
    }

}
