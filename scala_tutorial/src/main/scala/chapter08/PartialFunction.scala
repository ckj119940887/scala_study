package chapter08

object PartialFunction extends App {
  val list = List(("a", 12), ("b", 35), ("c", 29), ("d", 11))

  val newList = list.map(tuple => (tuple._1, tuple._2*2))
  println(newList)

  // pattern match
  val newList2 = list.map(
    tuple => {
      tuple match {
        case (word, coun) => (word, coun*2)
      }
    }
  )
  println(newList2)

  // partial function, 省去了lamda表达式
  val newList3 = list.map({
    case (word, coun) => (word, coun * 2)
  })
  println(newList3)

  // abs function
  // 偏函数的最后一个参数是返回类型
  val positiveAbs: PartialFunction[Int, Int] = {
    case x if x > 0 => x
  }

  val negativeAbs: PartialFunction[Int, Int] = {
    case x if x < 0 => -x
  }

  val zeroAbs: PartialFunction[Int, Int] = {
    case 0 => 0
  }

  def abs(x: Int): Int = (positiveAbs orElse negativeAbs orElse zeroAbs)(x)
  println(abs(-17))
}
