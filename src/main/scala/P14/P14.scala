package P14

import util.ExerciseTemplate

trait P14 extends ExerciseTemplate {
  /*
	P14 (*) Duplicate the elements of a list.
    Example:

    scala> duplicate(List('a, 'b, 'c, 'c, 'd))
    res0: List[Symbol] = List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)
	*/
  val name = "P14 (Duplicate the elements of a list)"
  def duplicate[T](l: List[T]): List[T]

  test("Invoking duplicate on an empty list should return an empty list") {
    assert(duplicate(List()) == List())
  }

  test("Invoking duplicate on a list should return a list") {
    assert(duplicate(List('a, 'b, 'c, 'c, 'd)) == List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd))
  }
}