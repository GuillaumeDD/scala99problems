package P18

object sol01 extends P18 {
  def slice[T](borneInf: Int, borneSup: Int, l: List[T]): List[T] =
    {
      require(borneInf <= borneSup)
      (borneInf, borneSup, l) match {
        case (_, _, List()) => List()
        case (0, 0, l) => List()
        case (0, sup, head :: tail) =>
          head +: slice(0, sup - 1, tail)
        case (inf, sup, head :: tail) =>
          slice(inf - 1, sup - 1, tail)
      }
    }
}