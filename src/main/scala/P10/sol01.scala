package P10

object sol01 extends P10 {
  import P09.sol01._
  def encode[T](l: List[T]): List[(Int, T)] =
    l match {
      case List() => List()
      case ll =>
        pack(ll).map(ls => (ls.size, ls.head))
    }
}