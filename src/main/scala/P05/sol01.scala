package P05

object sol01 extends P05 {
  def reverse[T](l: List[T]): List[T] =
    l match {
      case List() =>
        List()
      case head :: tail =>
        reverse(tail) :+ head
    }
}