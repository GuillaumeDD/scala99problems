package P07

object sol02 extends P07 {
  def flatten(l: List[Any]): List[Any] =
    l.flatMap({
      case list: List[Any] => flatten(list)
      case elt => List(elt)
    })
}