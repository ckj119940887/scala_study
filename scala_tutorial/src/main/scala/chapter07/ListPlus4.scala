package chapter07

object ListPlus4 extends App {
  // List.range, 第三个参数是步进step
  println(List.range(1,5,2))

  // List.fill, fill 方法创建包含零个或多个同一个元素拷贝的列表。
  // 它接收两个参数：要创建的列表长度和需要重复的元素 。
  println(List.fill(4)("a"))
  // 也可以用来创建多维列表
  println(List.fill(2,3)("b"))

  // List. tabulate, 表格化一个函数
  // 第一个参数列表给出要创建列表的维度，而第二个参数列表描述列表的元素 。
  val t = List.tabulate(5)(n => n * n)
  println(t)
  val s = List.tabulate(3,3)(_ * _)
  println(s)

  // List.concat, 将多个列表拼接在一起
  println(List.concat(List(1,2,3), List(4,5,6)))

  // zipped,不再只是针对单个列表而是能同时处理多个列表
  println((List(10, 20), List(3,4)).zipped.map(_ * _))

}
