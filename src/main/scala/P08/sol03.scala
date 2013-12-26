package P08

object sol03 extends P08 {
  def compress[T](l: List[T]): List[T] =
    l match {
      case List() =>
        List()
      case head :: tail =>
        List(head) ++ compress(tail.dropWhile(_ == head))
    }
}