package P05

import util.ExerciseTemplate

trait P05 extends ExerciseTemplate {
  /*
	P05 (*) Reverse a list.
    Example:

    scala> reverse(List(1, 1, 2, 3, 5, 8))
    res0: List[Int] = List(8, 5, 3, 2, 1, 1)
	*/
  val name = "P05 (Reverse a list)"
  def reverse[T](l: List[T]): List[T]

  test("Invoking reverse on a list should return its reverse") {
    assert(reverse(List(1, 1, 2, 3, 5, 8)) == List(8, 5, 3, 2, 1, 1))
    assert(reverse(List(1, 2, 3, 4, 5)) == List(5, 4, 3, 2, 1))
  }

  test("Invoking reverse on an empty list should return the empty list") {
    assert(reverse(List()) == List())
  }
}