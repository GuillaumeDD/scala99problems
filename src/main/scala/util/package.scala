package object util {
  /**
   * Returns a pseudorandom, uniformly distributed int value between 0 (inclusive)
   * and the specified value (exclusive)
   * @param borneSup Specified value (excluded)
   */
  def randomInt(borneSup: Int): Int =
    scala.util.Random.nextInt(borneSup)
}