package chapter04

object IfElse {
  def main(args: Array[String]): Unit = {
    val i = 11
    val test = if (i < 10) 10 else 11
    println(test)
    val test2 = if (i < 10) 10 else None
    println(test2)
  }
}
