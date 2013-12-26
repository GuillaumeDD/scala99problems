package P13

object sol01 extends P13 {
  def encodeDirect[T](l: List[T]): List[(Int, T)] =
    l match {
      case List() => List()
      case l =>
        val (packed, next) = l span (_ == l.head)
        (packed.size, packed.head) :: encodeDirect(next)
    }
}