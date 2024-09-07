package chapter05

object Lazy {
  def main(args: Array[String]): Unit = {
    lazy val result = sum(12,33)
    println("hello")
    println(result)
  }

  def sum(a:Int, b:Int) = {
    println("sum")
    a + b
  }
}
