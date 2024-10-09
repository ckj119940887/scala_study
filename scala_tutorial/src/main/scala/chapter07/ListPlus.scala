package chapter07

object ListPlus extends App {
  // scala也支持普通函数式编程中的列表用法
  val fruit = "apples" :: ("oranges" :: ("banana" :: Nil))
  println(fruit)

  // 返回第一个元素
  println(fruit.head)
  // 返回尾列表，除第一个元素外的所有元素
  println(fruit.tail)
  // 返回头列表，除最后一个元素外的所有元素
  println(fruit.init)
  // 返回最后一个元素
  println(fruit.last)
  // 判断是否为空
  println(fruit.isEmpty)

  // 匹配有三个元素的List
  val List(a,b,c) = fruit
  println(a)
  println(b)
  println(c)

  // 匹配长度大于等于2的List
  val x :: y :: rest = fruit
  println(rest)
}
