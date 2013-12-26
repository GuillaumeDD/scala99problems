package P20

import util.ExerciseTemplate

trait P20 extends ExerciseTemplate {
  /*
	P20 (*) Remove the Kth element from a list.
    Return the list and the removed element in a Tuple. Elements are numbered from 0.

    Example:

    scala> removeAt(1, List('a, 'b, 'c, 'd))
    res0: (List[Symbol], Symbol) = (List('a, 'c, 'd),'b)
    */
  val name = "P20 (Remove the Kth element from a list)"
  def removeAt[T](index: Int, l: List[T]): (List[T], T)

  test("Invoking removeAt on an empty list should produce a NoSuchElementException") {
    intercept[NoSuchElementException] {
      removeAt(1, List())
    }
  }

  test("Invoking removeAt with an index bigger than the list size should produce a NoSuchElementException") {
    intercept[NoSuchElementException] {
      removeAt(4, List('a, 'b, 'c, 'd))
    }
  }

  test("Invoking removeAt should remove the element of the list") {
    assert(removeAt(1, List('a, 'b, 'c, 'd)) == (List('a, 'c, 'd), 'b))
    assert(removeAt(0, List('a, 'b, 'c, 'd)) == (List('b, 'c, 'd), 'a))
    assert(removeAt(3, List('a, 'b, 'c, 'd)) == (List('a, 'b, 'c), 'd))
  }
}