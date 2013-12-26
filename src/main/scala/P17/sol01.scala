package P17

object sol01 extends P17 {
  def split[T](index: Int, l: List[T]): (List[T], List[T]) =
    {
      def splitHelper(curIndex: Int, curL: List[T]): (List[T], List[T]) =
        (curIndex, curL) match {
          case (_, List()) => (List(), List())
          case (1, head :: tail) =>
            (List(head), tail)
          case (_, head :: tail) =>
            val (pre, post) = splitHelper(curIndex - 1, tail)
            (head :: pre, post)
        }

      splitHelper(index, l)
    }
}