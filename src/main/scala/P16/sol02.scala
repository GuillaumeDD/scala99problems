package P16

import scala.annotation.tailrec

object sol02 extends P16 {
  def drop[T](freq: Int, l: List[T]): List[T] =
    {
      @tailrec
      def dropHelper[T](freqCompteur: Int, ls: List[T], acc: List[T]): List[T] =
        (freqCompteur, ls) match {
          case (_, List()) => acc
          case (1, _ :: tail) =>
            dropHelper(freq, tail, acc)
          case (_, head :: tail) =>
            dropHelper(freqCompteur - 1, tail, acc :+ head)
        }

      dropHelper(freq, l, List())
    }
}