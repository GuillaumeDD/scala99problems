package P15

object sol01 extends P15 {
  def duplicateN[T](rep: Int, l: List[T]): List[T] =
    l.flatMap(elt => List.fill(rep)(elt))
}