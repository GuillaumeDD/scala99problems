package P08

object sol01 extends P08 {
  def compress[T](l: List[T]): List[T] =
    {
      // Helper function
      def compressHelper[T](l: List[T], elt: T): List[T] = {
        l match {
          case List() =>
            l
          case head :: tail =>
            if (head == elt) {
              compressHelper(tail, elt)
            } else {
              head +: compressHelper(tail, head)
            }
        }
      }

      l match {
        case List() =>
          l
        case head :: tail =>
          head +: compressHelper(tail, head)
      }
    }
}