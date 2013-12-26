package P08

object sol04 extends P08 {
  def compress[T](l: List[T]): List[T] =
    (l foldRight List[T]())((head, list) =>
      if (list.isEmpty || head != list.head) {
        head :: list
      } else {
        list
      })
}