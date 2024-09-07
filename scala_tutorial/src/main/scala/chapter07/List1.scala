package chapter07

object List1 extends App {
  val list1 = List(1,2,3)
  list1.foreach(println)

  // List 不可以更改值, 和Array不一样
  // list1(1) = 10

  val list2 = list1 :+ 100
  val list3 = 101 +: list2
  println(list3)

  val list4 = 20 :: 13 :: Nil
  println(list4)

  // 两个List的连接使用的是:::,而不是::
  val list5 = list3 :: list2 // List(List(101, 1, 2, 3, 100), 1, 2, 3, 100)
  println(list5)

  val list6 = list3 ::: list2
  println(list6)

}
