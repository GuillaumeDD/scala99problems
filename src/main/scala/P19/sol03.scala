package P19

object sol03 extends P19 {
  def rotate[T](n: Int, l: List[T]): List[T] = {
    val rotations = if (l.isEmpty) 0 else n % l.size
    if (rotations < 0) {
      rotate(rotations + l.size, l)
    } else {
      (l drop rotations) ++ (l take rotations)
    }
  }
}