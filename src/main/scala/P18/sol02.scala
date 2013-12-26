package P18

object sol02 extends P18 {
  def slice[T](borneInf: Int, borneSup: Int, l: List[T]): List[T] =
    {
      require(borneInf <= borneSup)
      def sliceHelper[T](count: Int, lH: List[T], acc: List[T]): List[T] =
        (count, lH) match {
          case (_, List()) =>
            acc
          case (n, ls) if n >= borneSup =>
            acc
          case (n, head :: tail) if n >= borneInf =>
            sliceHelper(n + 1, tail, acc :+ head)
          case (n, _ :: tail) =>
            sliceHelper(n + 1, tail, acc)
        }
      sliceHelper(0, l, List())
    }
}