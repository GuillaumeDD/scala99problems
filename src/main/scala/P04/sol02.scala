package P04

import scala.annotation.tailrec

object sol02 extends P04 {

  def length[T](l: List[T]): Int = {
    // Helper function
    @tailrec
    def lengthHelper[T](l: List[T], acc: Int): Int =
      l match {
        case List() => acc
        case head :: tail =>
          lengthHelper(tail, acc + 1)
      }
    lengthHelper(l, 0)
  }
}