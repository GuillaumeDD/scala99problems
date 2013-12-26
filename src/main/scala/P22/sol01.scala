package P22

object sol01 extends P22 {
  def range(min: Int, max: Int): List[Int] =
    {
      require(min <= max)
      if (min == max) {
        List(min)
      } else {
        min :: range(min + 1, max)
      }
    }
}