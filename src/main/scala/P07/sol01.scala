package P07

object sol01 extends P07 {
  def flatten(l: List[Any]): List[Any] = {
    // Helper function
    def flattenAny(elt: Any): List[Any] =
      elt match {
        case l: List[Any] =>
          flatten(l)
        case e =>
          List(e)
      }

    l match {
      case List() =>
        List()
      case head :: tail =>
        flattenAny(head) ++ flatten(tail)
    }
  }
}