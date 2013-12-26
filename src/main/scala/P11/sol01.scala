package P11

object sol01 extends P11 {
  import P09.sol01._
  // Note the Either type associated with Left and Right
  def encodeModified[T](l: List[T]): List[Either[T, (Int, T)]] =
    l match {
      case List() => List()
      case ll =>
        pack(ll).map {
          l =>
            if (l.size == 1)
              Left(l.head)
            else
              Right((l.size, l.head))
        }
    }
}