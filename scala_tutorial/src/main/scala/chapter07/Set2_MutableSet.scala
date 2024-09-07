package chapter07

import scala.collection.mutable

object Set2_MutableSet extends App {
  // create
  val set1 = mutable.Set(1,2,3,4,5)
  println(set1)

  // add,  等价于 +=
  set1.add(111)
  println(set1)

  // delete, 等价于 -=
  set1.remove(111)
  println(set1)

  // combine two sets
  val set2 = mutable.Set(999,997)
  set2 ++= set1
  println(set2)
}
