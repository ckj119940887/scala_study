package chapter07.Common

object CommonOperation4 extends App {
  val list1 = List(1,2,3,4,5)

  // filter
  val filterList = list1.filter(_ % 2 == 0)
  println(filterList)

  // map
}
