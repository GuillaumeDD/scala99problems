package P16

object sol01 extends P16 {
  def drop[T](freq: Int, l: List[T]): List[T] =
    {
      def dropHelper[T](freqCompteur: Int, ls: List[T]): List[T] =
        (freqCompteur, ls) match {
          case (_, List()) => List()
          case (1, _ :: tail) =>
            dropHelper(freq, tail)
          case (_, head :: tail) =>
            head :: dropHelper(freqCompteur - 1, tail)
        }

      dropHelper(freq, l)
    }
}