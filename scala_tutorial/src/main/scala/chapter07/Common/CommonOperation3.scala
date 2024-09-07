package chapter07.Common

object CommonOperation3 extends App {
  val list1 = List(1,2,3,4,5)
  val list2 = List(("a", 1), ("b", 2), ("c", 3), ("d", 4))

  // sum
  println(list1.sum)

  // product
  println(list1.product)

  // min
  println(list1.min)
  println(list2.minBy(_._2))

  // max
  println(list1.max)
  println(list2.maxBy((tuple: (String, Int)) => tuple._2))
  println(list2.maxBy(_._2))

  // sort
  val sortList1 = list1.sorted
  println(sortList1)

  // 从大到小
  val sortList11 = list1.sorted(Ordering[Int].reverse)
  println(sortList11)

  val sortList2 = list2.sortBy(_._2)
  val sortList22 = list2.sortBy(_._2)(Ordering[Int].reverse)

  // sortWith
  println(list1.sortWith((a: Int, b:Int) => {a < b}))
  println(list1.sortWith(_ < _))
}
