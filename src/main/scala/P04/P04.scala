package P04

import util.ExerciseTemplate

trait P04 extends ExerciseTemplate {
  /*
	P04 (*) Find the number of elements of a list.
    Example:

    scala> length(List(1, 1, 2, 3, 5, 8))
    res0: Int = 6
	*/
  val name = "P04 (Find the number of elements of a list)"
  def length[T](l: List[T]): Int
  
  test("Invoking length on a list should return its length") {
    assert(length(List()) == 0)
    assert(length(List(2,1)) == 2)
    assert(length(List(1, 1, 2, 3, 5, 8)) == 6)
  }
}