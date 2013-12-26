package P08

import scala.annotation.tailrec

object sol02 extends P08 {
  def compress[T](l: List[T]): List[T] =
    {
      // Helper function
      @tailrec
      def compressHelper[T](l: List[T], elt: T, result: List[T]): List[T] = {
        l match {
          case List() =>
            result
          case head :: tail =>
            if (head == elt) {
              compressHelper(tail, elt, result)
            } else {
              compressHelper(tail, head, result :+ head)
            }
        }
      }

      l match {
        case List() =>
          l
        case head :: tail =>
          compressHelper(tail, head, head +: List())
      }
    }
}