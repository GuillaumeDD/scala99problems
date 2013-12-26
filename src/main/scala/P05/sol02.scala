package P05

import scala.annotation.tailrec

object sol02 extends P05 {
  def reverse[T](l: List[T]): List[T] =
    {
      // Helper function
      @tailrec
      def reverseHelper[T](l: List[T], result: List[T]): List[T] =
        l match {
          case List() =>
            result
          case head :: tail =>
            reverseHelper(tail, head +: result)
        }

      reverseHelper(l, List())
    }
}