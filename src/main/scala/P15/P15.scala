package P15

import util.ExerciseTemplate

trait P15 extends ExerciseTemplate {
  /*
	P15 (**) Duplicate the elements of a list a given number of times.
    Example:

    scala> duplicateN(3, List('a, 'b, 'c, 'c, 'd))
    res0: List[Symbol] = List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)
	*/
  val name = "P15 (Duplicate the elements of a list a given number of times)"
  def duplicateN[T](rep: Int, l: List[T]): List[T]

  test("Invoking duplicateN on an empty list should return an empty list") {
    assert(duplicateN(3, List()) == List())
  }

  test("Invoking duplicateN on a list should return a list") {
    assert(duplicateN(3, List('a, 'b, 'c, 'c, 'd)) == List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd))
  }
}