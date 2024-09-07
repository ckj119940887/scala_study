package chapter07

object Tuple1 extends App {
  val tuple1 = ("a", 1, 3)
  println(tuple1)
  println(tuple1._1)
  println(tuple1._2)
  println(tuple1._3)

  for(elem <- tuple1.productIterator)
    println(elem)

  // 嵌套元组
  val tuple2 = (1,2,3,(4,5))
  println(tuple2._4._1)
}
