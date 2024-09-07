package chapter04

object For {
  def main(args: Array[String]): Unit = {
    // 范围遍历 (1 <= i <= 10)
    for (i <- 1 to 10)
      println(i)

    // (1 <= i < 10)
    for (i <- Range(1,10)) {
      println(i)
    }

    // 循环步长, step=2
    for (i <- Range(1,10,2))
      println(i)

    // 循环步长, step=-2
    for (i <- 10 to 2 by -2)
      println(i)

    // (1 <= i < 10)
    for (i <- 1 until 10)
      println(i)

    // 集合,List,Set遍历
    for (i <- Array(12, 34, 56))
      println(i)

    // 循环守卫, 没有break,continue关键字
    for (i <- 1 to 10 if i != 2)
      println(i)

    // 循环嵌套
    for (i <- 1 to 3; j <- 2 to 4)
      println(i*j)

    // 循环引入变量
    for (i <- 1 to 10; j = i - 1)
      println(j)

    // 循环返回值，默认返回值都是空
    val res = for (i <- 1 to 10) yield i
    println(res)
  }

}
