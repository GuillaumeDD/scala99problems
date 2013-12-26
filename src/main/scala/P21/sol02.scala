package P21

import scala.annotation.tailrec

object sol02 extends P21 {
  def insertAt[T](elt: T, index: Int, l: List[T]): List[T] = {
    @tailrec
    def insertAtHelper(n: Int, ls: List[T], acc: List[T]): List[T] =
      (n, ls) match {
        case (_, List()) => acc ::: List(elt)
        case (0, l) => acc ::: (elt :: l)
        case (n, head :: tail) =>
          insertAtHelper(n - 1, tail, acc :+ head)
      }

    insertAtHelper(index, l, List())
  }
}