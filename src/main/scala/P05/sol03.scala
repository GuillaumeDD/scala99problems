package P05

object sol03 extends P05 {
  def reverse[T](l: List[T]): List[T] =
    (l foldLeft List[T]())((newList, elt) => (elt +: newList))
}