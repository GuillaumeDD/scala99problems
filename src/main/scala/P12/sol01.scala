package P12

object sol01 extends P12 {
  def decode[T](l: List[(Int, T)]): List[T] =
    l match {
      case List() => List()
      case head :: tail =>
        val (i, c) = head
        List.fill(i)(c) ++ decode(tail)
    }
}