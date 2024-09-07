package chapter07

import scala.collection.mutable.ListBuffer

object List2_MutableList extends App {
  val list1 = new ListBuffer[Int]()
  val list2 = ListBuffer(1,2,3)
  println(list2)

  list2.append(11)
  list2.prepend(20,21,22)
  println(list2)

  // List也支持 += +=:

  val list3 = list1 ++ list2
  println(list3)

  val list4 = list2 ++= list2
  println(list4)

  list4(0) = 999
  println(list4)

  list4.remove(1,2)
  println(list4)
}
