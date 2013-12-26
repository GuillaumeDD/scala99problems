package P24

object sol01 extends P24 {
  import P22.sol01._
  import P23.sol01._

  /**
   * Returns n different random numbers between 1 and max.
   * Due to randomSelect and range, the obtained list is always sorted with this
   * implementation.
   */
  def lotto(n: Int, max: Int): List[Int] =
    {
      require(n <= max + 1)
      randomSelect(n, range(1, max))
      // Shuffled version:
      // scala.util.Random.shuffle(randomSelect(n, range(1, max)))
    }
}