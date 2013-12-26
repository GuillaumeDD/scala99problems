package P17

import util.ExerciseTemplate
import scala.annotation.tailrec

object sol02 extends P17 {
  def split[T](index: Int, l: List[T]): (List[T], List[T]) =
    {
      @tailrec
      def splitHelper(curIndex: Int, curL: List[T], pre: List[T]): (List[T], List[T]) =
        (curIndex, curL) match {
          case (_, List()) => (pre, List())
          case (1, head :: tail) =>
            (pre :+ head, tail)
          case (n, head :: tail) =>
            splitHelper(n - 1, tail, pre :+ head)

        }

      splitHelper(index, l, List())
    }
}