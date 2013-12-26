package P08

import util.ExerciseTemplate

trait P08 extends ExerciseTemplate {
  /*
	P08 (**) Eliminate consecutive duplicates of list elements.
    If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed.

    Example:

    scala> compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    res0: List[Symbol] = List('a, 'b, 'c, 'a, 'd, 'e)
	*/
  val name = "P08 (Eliminate consecutive duplicates of list elements)"
  def compress[T](l: List[T]): List[T]

  test("Invoking compress on an empty list return the empty list") {
    assert(compress(List()) == List())
  }

  test("Invoking compress on a list shoud compress the list") {
    assert(compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) == List('a, 'b, 'c, 'a, 'd, 'e))
  }
}