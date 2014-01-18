package arithmetic.P31

import org.scalatest.Args

class sol02 extends P31 {
  def isPrime(n: Int): Boolean =
    {
      require(n >= 0)
      (n > 1) && ((2 to Math.sqrt(n).floor.toInt) forall (n % _ != 0))
    }
}