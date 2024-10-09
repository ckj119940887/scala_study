package chapter08

// 样例类，自动将对应的类的Object实现出来，同时提供了apply方法和unapply方法
// 类名参数列表中的val可以直接省略了
case class Student1(name: String, age: Int)

object PatternMatch4 extends App {
  val student = Student1("ckj", 11)

  val result = student match {
    case Student1("ckj", 11) => "ckj, 18"
    case _ => "Else"
  }

  println(result)
}
