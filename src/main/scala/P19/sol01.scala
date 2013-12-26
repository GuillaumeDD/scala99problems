package P19

object sol01 extends P19 {
  def rotate[T](n: Int, l: List[T]): List[T] =
    (n, l) match {
      case (0, _) => l
      case (_, List()) => List()
      case (n, head :: tail) if n >= 0 =>
        rotate(n - 1, tail :+ head)
      case (n, ls) if n < 0 =>
        rotate(n + 1, ls.last :: ls.init)
    }
}