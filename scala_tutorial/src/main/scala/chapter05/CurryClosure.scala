package chapter05

object CurryClosure {
  def main(args: Array[String]): Unit = {
    // closure
    def add(a: Int, b: Int) = a + b

    val add4 = add(4, _)
    println(add4(1))

    // currying, 把一个参数列表变成多个参数列表，每提供一个参数，就返回一个函数，用作下一个输入的参数
    def addCurrying(a: Int)(b: Int): Int = a + b
    val addCurryingTest = addCurrying(4) _
    println(addCurryingTest(5))

  }

}
