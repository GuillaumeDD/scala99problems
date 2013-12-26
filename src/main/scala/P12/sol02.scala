package P12

object sol02 extends P12 {
  def decode[T](l: List[(Int, T)]): List[T] =
    l.flatMap(elt => List.fill(elt._1)(elt._2))
}