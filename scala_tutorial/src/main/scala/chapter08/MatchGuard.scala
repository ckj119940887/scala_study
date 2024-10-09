package chapter08

object MatchGuard extends App {
  def abs(num: Int): Int = {
    num match {
      case i if i>0 => i
      case i if i<0 => -i
    }
  }

  println(abs(-5))
}
