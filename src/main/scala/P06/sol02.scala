package P06

object sol02 extends P06 {
  def isPalindrome[T](l: List[T]): Boolean = {
    def isPalindrome(start: Int, end: Int): Boolean =
      if (start >= end)
        true
      else {
        if (l(start) == l(end)) {
          isPalindrome(start + 1, end - 1)
        } else {
          false
        }
      }
    isPalindrome(0, l.size - 1)
  }
}