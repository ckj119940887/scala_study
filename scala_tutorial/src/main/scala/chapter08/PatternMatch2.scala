package chapter08

object PatternMatch2 extends App {
  // tuple
  for(tuple <- List(
    (0),
    (1,0),
    (0,0,0),
    (1,1,0)
  )) {
    val result = tuple match {
      case (0) => "0"
      case (x, y) => "tuple: " + x + " " + y
      case _ => "Not Found"
    }
    println(result)
  }

  // match when variable declare
  val (x, y) = (19, "hello")
  println(s"x:$x, y:$y")

  // rest并不是尾部的列表，这里会报错
  //  val List(first, second, rest) = List(23, 14, 55, 39)
  //  println(s"first:$first, second:$second, rest:$rest")

  val first :: second :: rest = List(23, 24, 55, 39)
  println(s"first:$first, second:$second, rest:$rest")

  // match in for statement
  val list:List[(String, Int)] = List(("a", 12), ("b", 13), ("c", 14))
  for((word, count) <- list) {
    println(word + " " + count)
  }

  for(("a", count) <- list) {
    println(count)
  }
}
