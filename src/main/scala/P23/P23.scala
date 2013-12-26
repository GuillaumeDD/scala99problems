package P23

import util.ExerciseTemplate

trait P23 extends ExerciseTemplate {
  /*
	P23 (**) Extract a given number of randomly selected elements from a list.
    Example:

    scala> randomSelect(3, List('a, 'b, 'c, 'd, 'f, 'g, 'h))
    res0: List[Symbol] = List('e, 'd, 'a)

    Hint: Use the solution to problem P20
	*/
  val name = "P23 (Extract a given number of randomly selected elements from a list)"
  def randomSelect[T](nb: Int, l: List[T]): List[T]

  test("Invoking randomSelect returns a list of size nb which elements are included in the first list") {
    val l = List('a, 'b, 'c, 'd, 'f, 'g, 'h)
    val randomList = randomSelect(3, l)
    // Size
    assert(randomList.size == 3)
    // Inclusion of randomList into l
    assert(randomList.forall(l.contains(_)))
  }

  test("Invoking randomSelect with n smaller than the list size triggers an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      randomSelect(3, List())
    }

    intercept[IllegalArgumentException] {
      randomSelect(4, List(3, 2, 1))
    }
  }
}