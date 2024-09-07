package chapter07

object Set1 extends  App {
  // Set无序，但无重复元素
  val set1 = Set(1,3,2,2)
  println(set1)

  // add
  val set2 = set1 + 10
  println(set2)

  // concat
  val set3 = Set(67,68,60)
  val set4 = set3 ++ set2
  println(set4)

  // delete
  val set5 = set4 - 60
  println(set5)
}
