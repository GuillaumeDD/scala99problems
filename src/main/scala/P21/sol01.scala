package P21

object sol01 extends P21 {
  def insertAt[T](elt: T, index: Int, l: List[T]): List[T] =
    (index, l) match {
      case (_, List()) => List(elt)
      case (0, l) => elt :: l
      case (n, head :: tail) =>
        head :: insertAt(elt, n - 1, tail)
    }
}