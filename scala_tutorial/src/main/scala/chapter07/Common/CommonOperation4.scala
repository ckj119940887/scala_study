package chapter07.Common

object CommonOperation4 extends App {
  val list1 = List(1,2,3,4,5)

  // exists
  println(list1.exists(_ > 3))

  // filter
  // val filterList =list1.filter((elem: Int) => {elem % 2 == 0})
  val filterList = list1.filter(_ % 2 == 0)
  println(filterList)

  // map
  val mapList = list1.map((elem: Int) => {elem * 2})
  val sqrtList = list1.map(x => x * x)
  println(sqrtList)

  // flat
  val nestList: List[List[Int]] = List(List(1,2,3), List(4,5), List(6,7,8,9))
  //val flatList = nestList(0) ::: nestList(1) ::: nestList(2)
  val flatList = nestList.flatten
  println(flatList)

  // flat-map
  val strings: List[String] = List("hello world", "hello scala", "hello ocaml")
  //  val splitList = strings.map(string => string.split(" "))
  //  val flatSplitList = splitList.flatten
  //  println(flatSplitList)
  val flatSplitList = strings.flatMap(string => string.split(" "))
  println(flatSplitList)

  // group
  val groupList = list1.groupBy(_ % 2 == 1)
  val oddList = groupList.get(true).get
  val evenList = groupList.get(false).get
  println(oddList)
  println(evenList)
}
