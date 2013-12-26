package P17

import util.ExerciseTemplate

trait P17 extends ExerciseTemplate {
  /*
	P17 (*) Split a list into two parts.
    The length of the first part is given. Use a Tuple for your result.

    Example:

    scala> split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
    res0: (List[Symbol], List[Symbol]) = (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
	*/
  val name = "P17 (Split a list into two parts)"
  def split[T](index: Int, l: List[T]): (List[T], List[T])

  test("Invoking split with an index superior to the list size should return the list") {
    assert(split(3, List()) == (List(), List()))
    assert(split(3, List('a)) == (List('a), List()))
  }

  test("Invoking split on a list should split the list") {
    assert(split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == (List('a, 'b, 'c), List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  }
}