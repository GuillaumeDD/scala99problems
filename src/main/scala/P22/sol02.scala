package P22

import scala.annotation.tailrec

object sol02 extends P22 {
  def range(min: Int, max: Int): List[Int] =
    {
      require(min <= max)
      @tailrec
      def rangeHelper(bInf: Int, acc: List[Int]): List[Int] =
        if (bInf == max) {
          acc :+ bInf
        } else {
          rangeHelper(bInf + 1, acc :+ bInf)
        }

      rangeHelper(min, List())
    }
}