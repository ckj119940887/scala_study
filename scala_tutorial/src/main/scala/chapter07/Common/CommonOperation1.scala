package chapter07.Common

object CommonOperation1 extends App {
  val list1 = List(1,2,3)
  val set1 = Set(1,2,3,4,5)

  // length
  println(list1.length)
  println(list1.size)
  println(set1.size)

  // traverse
  for(elem <- list1)
    println(elem)

  set1.foreach(println)

  // iterator
  for(elem <- list1.iterator)
    println(elem)
}
