package arithmetic

object Util {

  /**
   *
   * @return A stream of integer from n
   */
  def from(n: Int): Stream[Int] =
    n #:: from(n + 1)

  /**
   * Implements a modified, less efficient version of the sieve of Eratosthenes.
   * (See "The Genuine Sieve of Eratosthenes" by Melissa E. O'Neill)
   * @return The prime numbers from n (n should be prime)
   */
  private def sieve(s: Stream[Int]): Stream[Int] =
    s.head #:: sieve(s.tail.filter(_ % s.head != 0))

  /**
   * Prime numbers as a stream
   */
  val primes: Stream[Int] =
    sieve(from(2))

  // Solution through the stream of primes
  def listPrimesinRange(range: Range): List[Int] =
    primes.dropWhile(_ < range.start).takeWhile(_ <= range.end).toList
    /*
     *   test("Taking 25 prime numbers should return the 25 first prime numbers") {
    assert(arithmetic.Util.primes.take(25).toList ==
      List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97))
  }
     */
}