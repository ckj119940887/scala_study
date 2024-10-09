package chapter10

object Implicit2 extends App {
  // implicit class
  // 隐式类，不能放在顶层位置，即不能放在与Implict2同级位置
  implicit class MyRichInt2(val self: Int) {
    def myMax2(n: Int): Int = {
      if(n < self)
        self
      else
        n
    }

    def myMin2(n: Int): Int = {
      if(n < self)
        n
      else
        self
    }
  }

  println(12.myMax2(14))
}
