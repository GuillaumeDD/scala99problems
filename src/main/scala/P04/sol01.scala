package P04

object sol01 extends P04 {
  def length[T](l: List[T]): Int =
    l match {
      case List() => 0
      case head :: tail =>
        1 + length(tail)
    }
}