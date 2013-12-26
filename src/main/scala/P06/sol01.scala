package P06

object sol01 extends P06 {
  def isPalindrome[T](l: List[T]): Boolean =
    l == l.reverse
}