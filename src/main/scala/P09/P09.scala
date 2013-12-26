package P09

import util.ExerciseTemplate

trait P09 extends ExerciseTemplate {
  /*
	P09 (**) Pack consecutive duplicates of list elements into sublists.
    If a list contains repeated elements they should be placed in separate sublists.

    Example:

    scala> pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    res0: List[List[Symbol]] = List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))
   */
  val name = "P09 (Pack consecutive duplicates of list elements into sublists)"
  def pack[T](l: List[T]): List[List[T]]

  test("Invoking pack on an empty list should return a packed empty list") {
    assert(pack(List()) == List(List()))
  }

  test("Invoking pack on a list should return a packed list") {
    assert(pack(List('a, 'b, 'c)) == List(List('a), List('b), List('c)))
    assert(pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) == List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e)))
  }
}