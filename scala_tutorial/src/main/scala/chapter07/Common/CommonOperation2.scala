package chapter07.Common

object CommonOperation2 extends App {
  val list1 = List(1,2,3,4)
  val list2 = List(4,6,7,8)

  // head
  println(list1.head)

  // last
  println(list1.last)

  // init, 与tail相对
  println(list1.init)

  // tail
  println(list1.tail)

  // reverse
  println(list1.reverse)

  // take
  println(list1.take(2))
  println(list1.takeRight(2))

  // drop
  println(list1.drop(2))
  println(list1.dropRight(2))

  // union
  val u = list1.union(list2)
  println(u)

  // intersect
  val i = list1.intersect(list2)
  println(i)

  // diff
  val diff1 = list1.diff(list2)
  println(diff1)

  // 两个集合中的元素一一配对
  // val z = list1.zip(list2)
  val z = list1 zip list2
  println(z)

  // slide window
  for(elem <- list1.sliding(2))
    println(elem)

  for(elem <- list1.sliding(2,2))
    println(elem)
}
