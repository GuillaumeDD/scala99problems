package P06

import util.ExerciseTemplate

trait P06 extends ExerciseTemplate {
  /*
	P06 (*) Find out whether a list is a palindrome.
    Example:

    scala> isPalindrome(List(1, 2, 3, 2, 1))
    res0: Boolean = true
	*/
  val name = "P06 (Find out whether a list is a palindrome)"
  def isPalindrome[T](l: List[T]): Boolean

  test("An empty list is a palindrome") {
    assert(isPalindrome(List()))
  }

  test("A one-element list is a palindrome") {
    assert(isPalindrome(List(42)))
  }

  test("Invoking isPalindrome on a list should return if the list is a palindrome") {
    assert(isPalindrome(List('a, 'a)))
    assert(!isPalindrome(List(1, 2)))

    assert(isPalindrome(List(2, 4, 2)))
    assert(!isPalindrome(List(1, 2, 3)))

    assert(isPalindrome(List(1, 2, 2, 1)))
    assert(!isPalindrome(List(4, 3, 2, 1)))

    assert(isPalindrome(List(1, 2, 3, 2, 1)))
    assert(!isPalindrome(List(1, 2, 3, 4, 5)))
  }
}