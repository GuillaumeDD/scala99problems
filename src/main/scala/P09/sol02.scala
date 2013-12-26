package P09

object sol02 extends P09 {
  def pack[T](l: List[T]): List[List[T]] =
    if (l.isEmpty) List(List())
    else {
      // span : a pair consisting of the longest prefix of this list whose elements all satisfy p, and the rest of this list.
      val (packed, next) = l span { _ == l.head }
      if (next == Nil)
        List(packed)
      else
        packed :: pack(next)
    }
}