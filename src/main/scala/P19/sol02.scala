package P19

object sol02 extends P19 {
  def rotate[T](n: Int, l: List[T]): List[T] = {
    val rotations = if (l.isEmpty) 0 else n % l.size
    if (rotations < 0) {
      rotate(rotations + l.size, l)
    } else {
      (rotations, l) match {
        case (0, _) => l
        case (_, List()) => List()
        case (r, head :: tail) if n >= 0 =>
          rotate(r - 1, tail :+ head)
      }
    }
  }
}