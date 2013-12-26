package P20

import scala.annotation.tailrec

object sol01 extends P20 {
  def removeAt[T](index: Int, l: List[T]): (List[T], T) =
    {
      require(index >= 0)
      @tailrec
      def removeAtHelper[T](n: Int, pre: List[T], ls: List[T]): (List[T], T) =
        (n, ls) match {
          case (_, List()) => throw new NoSuchElementException()
          case (0, head :: tail) =>
            (pre ++ tail, head)
          case (_, head :: tail) =>
            removeAtHelper(n - 1, pre :+ head, tail)
        }

      removeAtHelper(index, List(), l)
    }
}