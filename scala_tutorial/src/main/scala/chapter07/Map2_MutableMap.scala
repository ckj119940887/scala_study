package chapter07

import scala.collection.mutable

object Map2_MutableMap extends App {
  val map1: mutable.Map[String, Int] = mutable.Map("a" -> 1, "b" -> 2)

  // mutable.Map 拥有put方法
  // map1 += (("c", 3)), 这里必须是两层括号
  map1.put("c", 3)
  println(map1)

  // update
  map1.update("a", 999)
  println(map1)

  // remove
  map1.remove("a")
  println(map1)

  // concat two maps
  val map2: Map[String, Int] = Map("d" -> 5, "e" -> 6)
  map1 ++= map2
  println(map1)
}
