package P12

import util.ExerciseTemplate

trait P12 extends ExerciseTemplate {
  /*
	P12 (**) Decode a run-length encoded list.
    Given a run-length code list generated as specified in problem P10, construct its uncompressed version.

    Example:

    scala> decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))
    res0: List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
	*/
  val name = "P12 (Decode a run-length encoded list)"
  def decode[T](l: List[(Int, T)]): List[T]

  test("Invoking decode on an empty list should return an empty list") {
    assert(decode(List()) == List())
  }

  test("Invoking decode on a list should return the decoded list") {
    assert(decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))) == List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  }
}