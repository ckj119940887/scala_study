package chapter10

object Implicit3 extends App {
  implicit val str: String = "alice"
  implicit val num: Int = 18

  def sayHello(name: String = "allice"): Unit = {
    println(name)
  }
  sayHello()

  // 使用隐式参数，需要提前告知隐式参数的位置
  def sayHi(implicit name: String) = println(name)

  sayHi
  // sayHi(),这里需要注意调用的形式，因为隐式参数在scala的底层实现中是柯里化
  // 所以只列不能加括号，如果函数原型是 def sayHi()(implicit name: String)，则可以加括号
  // 这里隐式参数的名字和定义的名字是不一样的，这里是匹配的是类型，而不是名字
  // 在作用范围内，同类型的隐式参数只能有一个，如果此时再定义有一个则会报错，比如 implicit val str2: string = "ckj"

  def sayHi2()(implicit name: String): Unit = println(name)
  sayHi2()

  // 这里定义的隐式参数会覆盖掉默认值
  def sayHi3(implicit name: String = "ckj") = println(name)
  sayHi3

  // 这里没有明确指明隐式参数，但是直接通过implicitly[Int]来表明需要Int型的隐式参数
  def hiAge(): Unit = println("hi " + implicitly[Int])
  hiAge()
}

