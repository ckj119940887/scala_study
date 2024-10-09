package chapter07.Common

object CommonOperation5 extends App {
  val list1 = List(1,2,3,4,5)

  // reduce
  val sums = list1.reduce(_ + _)
  println(sums)

  //val leftSub = list1.reduceLeft(_ - _)
  val leftSub = list1.reduceLeft((a: Int, b: Int) => {println(s"$a -$b"); a - b})
  println(leftSub)

  val rightSub = list1.reduceRight((a: Int, b: Int) => {println(s"$a -$b"); a - b})
  println(rightSub)

  println("===================")

  // fold = foldLeft
  val foldLeftSub = list1.foldLeft(10)((a: Int, b: Int) => {println(s"$a -$b"); a - b})
  println(foldLeftSub)

  val foldRightSub = list1.foldRight(10)((a: Int, b: Int) => {println(s"$a -$b"); a - b})
  println(foldRightSub)
}
