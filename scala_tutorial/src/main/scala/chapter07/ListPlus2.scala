package chapter07

object ListPlus2 extends App {
  val l1 = List(1,2,3)
  val l2 = List(4,5,6)

  // concat two list
  println(l1 ::: l2)

  // return the length
  println(l1.length)

  // reverse the list
  println(l1.reverse)

  // take the first n elements
  println(l1.take(2))

  // drop teh first n elements and return the left elements
  println(l1.drop(1))

  // 从指定的下标位置切开，返回这两个列表组成的pair
  val l3 = l1 ::: l2
  println(l3.splitAt(3))

  // 返回列表有效下标的列表
  println(l3.indices)
  for(i <- l3.indices)
    println(l3(i))

  // flatten
  val l4 = List(List(1,2), List(3,4))
  println(l4.flatten)

  // zip, 如果两个列表长度不同，那么没有配对的元素将会被丢弃
  val l5 = l1 zip l2
  println(l5)

  // zipWithIndex
  val l6 = l1.zipWithIndex
  println(l6)

  // unzip
  val l7 = l6.unzip
  println(l7._1)

  // List.toArray VS Array.toList
  val l8 = l1.toArray
  println(l8)
  val l9 = l8.toList
  println(l9)

  // iterator
  val it = l1.iterator
  while(it.hasNext)
    println(it.next())
}
