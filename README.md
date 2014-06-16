# Description #
This project provides some solutions to the [Ninety-Nine Scala Problems](http://aperiodic.net/phil/scala/s-99/).

# Installation #
You can run the solutions via SBT:

    $ sbt test

# Recipes #

## ...to add a problem ##

Every  problem belongs  to  a category.   Every  category (working  with
lists,  arithmetic,  logic  and  codes, binary  trees,  multiway  trees,
graphs,  misc) has  its own  package. A  problem and  its solutions  are
regrouped in a subpackage of a category package. Then, the recipe is:
 1. Find the category package (or create it, if it does not exist)
 2. Create a subpackage referring to the problem (e.g., "P42")
 3. Create a trait for the problem that extends the trait
   "ExerciseTemplate"
   * Define in commentary the problem
   * Give a name to your problem by implementing the field "name"
   * Declare the prototype of the function that represents the solution
   * Write  some  tests   ("ExerciceTemplate"  extends  "FunSuite"  from
     scalatest)

It should look like:
```scala
package list.P01

import org.scalatest.FunSuite
import util.ExerciseTemplate

trait P01 extends ExerciseTemplate {
  /*
	P01 (*) Find the last element of a list.
    Example:

    scala> last(List(1, 1, 2, 3, 5, 8))
    res0: Int = 8
  */
  val name = "P01 (Find the last element of a list)"
  def last[T](l: List[T]): T

  // Tests
  test("Invoking last on non-empty lists should return the last element") {
    assert(8 == last(List(1, 1, 2, 3, 5, 8)))
    assert(69 == last(List(69)))
  }

  test("Invoking last on an empty list should produce NoSuchElementException") {
    intercept[NoSuchElementException] {
      last(List())
    }
  }
}
```

## ...to add a solution ##

Once a problem is created, you can add a solution. All you have to do is
to implement in a class the trait that you have defined.

# License #
GPLv3 - see the COPYING file.
