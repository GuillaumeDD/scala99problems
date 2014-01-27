package arithmetic.P36
import util.tictac

object BenchmarkP36 extends App {
  // Loading primes
  val primes = arithmetic.P31.sol03.primes
  // Benchmark
  tictac("P36-sol01") {
    (new sol01()).primeFactorMultiplicity(315)
  }

  tictac("P36-sol01") {
    (new sol01()).primeFactorMultiplicity(315)
  }

  tictac("P36-sol02") {
    (new sol02()).primeFactorMultiplicity(315)
  }

  tictac("P36-sol03") {
    (new sol03()).primeFactorMultiplicity(315)
  }
}
