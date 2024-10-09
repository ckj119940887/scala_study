package Other

case class BinOp(op: String, num1: Int, num2: Int)

object PatternMatching extends App {
  // _* 代表的是多个参数，_代表的是一个元素的占位符
  // @ 可以用来临时指定匹配元素的名字
  def test1(x: Any) = x match {
    case List(0, _) => println("list start with 0, but only have two elements")
    case List(0, _*) => println("list start with 0")
    case List(5, l @ List(0, _*)) => println(s"list start with 5, but second element is a ${l}")
    case BinOp("+", 1, 2) => println("it is a BinOp object")
  }

  test1(List(0,0))
  test1(List(0,0,1,2,3))
  test1(List(5, List(0, 1,2,3)))
  test1(BinOp("+", 1, 2))
}
