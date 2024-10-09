package chapter08

object MatchCase extends App {
  val x: Int = 2
  // 这里注意，不需要break，一旦匹配了不会执行后面的
  val y: String = x match {
    case 1 => "one"
    case 2 => "two"
    case 3 => "three"
    case _ => "other"
  }
  println(y)

  val a = 11
  val b = 4

  def matchDualOp(op: Char):Any = op match {
    case '+' => a+b
    case '-' => a-b
    case '/' => a/b
    case '*' => a*b
    case _ => "illegal char"
  }

  println(matchDualOp('+'))
}
