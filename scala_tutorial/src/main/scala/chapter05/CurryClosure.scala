package chapter05

object CurryClosure {
  def main(args: Array[String]): Unit = {
    // closure
    def add(a: Int, b: Int) = a + b

    val add4 = add(4, _)
    println(add4(1))

    // currying, 把一个参数列表变成多个参数列表
    def addCurrying(a: Int)(b: Int): Int = a + b
    println(addCurrying(4)(5))

  }

}
