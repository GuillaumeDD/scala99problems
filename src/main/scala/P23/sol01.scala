package P23

import util._
import P20.sol01._
import scala.annotation.tailrec

object sol01 extends P23 {
  @tailrec
  def randomSelect[T](nb: Int, l: List[T]): List[T] =
    {
      require(nb <= l.size, s"$nb is greater than the list size ($l.size)")
      if (nb == l.size) {
        l
      } else {
        // Index random selection (between 0 (included) and the list size (excluded)) 
        val randomIndex = randomInt(l.size)
        // Removal of the list element
        val (newList, _) = removeAt(randomIndex, l)
        // Random selection of other element
        randomSelect(nb, newList)
      }
    }
}