package P14

object sol01 extends P14 {
  def duplicate[T](l: List[T]): List[T] =
    l.flatMap(elt => List(elt, elt))
}