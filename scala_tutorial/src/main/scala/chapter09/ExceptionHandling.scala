package chapter09

object ExceptionHandling extends App {
  try{
    val n = 10 / 0
  } catch {
    case e: ArithmeticException => {
      println("arith exception")
    }
    case e: Exception => {
      println("normal exception")
    }
  } finally {

  }
}
