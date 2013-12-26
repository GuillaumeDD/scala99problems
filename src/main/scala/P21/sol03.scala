package P21

object sol03 extends P21 {
  def insertAt[T](elt: T, index: Int, l: List[T]): List[T] =
    l.splitAt(index) match {
      case (pre, post) =>
        pre ::: List(elt) ::: post
    }
}