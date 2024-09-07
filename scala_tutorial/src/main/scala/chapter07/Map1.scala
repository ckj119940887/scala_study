package chapter07

object Map1 extends App {
  val map1: Map[String, Int] = Map("a" -> 1, "b" -> 2)
  println(map1)
  map1.foreach(println)

  map1.foreach((kv: (String,Int)) => println(kv))

  for(key <- map1.keys) {
    println(s"$key -- ${map1.get(key)}")
    println(s"$key -- ${map1.get(key).get}")
    // 如果找不到对应的值，则返回设置的值0
    println(s"$key -- ${map1.getOrElse("d", 0)}")
  }
}
