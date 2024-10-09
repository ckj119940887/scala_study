package chapter08

object PatternMatch1 extends App {
  // constant
  def describeConst(x: Any): String = x match {
    case 1 => "Num one"
    case "hello" => "String hello"
    case true => "Boolean true"
    case '+' => "Char +"
    case _ => "Not Found"
  }

  println(describeConst(0.3))

  // type
  def describeType(x: Any): String = x match {
    case i: Int => "Int " + i
    case s: String => "String " + s
    case l: List[String] => "List[String] " + l
    case _ => "Not Found"
  }

  println(describeType(List("123", "35", "abc")))

  // data collection
  for(arr <- List(
    Array(0),
    Array(1,0),
    Array(0,1,0),
    Array("hello", 20, 30)
  )) {
    val result = arr match {
      case Array(0) => "0"
      case Array(1,0) => "Array(1,0)"
      case Array(x, y) => "Array: " + x + " " + y
      case Array(0, _) => "Array start with 0"
      case Array(_, 1, _) => "Array mid is 1"
      case _ => "Not found"
    }

    println(result)
  }

  // List
  // method1
  for(list <- List(
    List(0),
    List(1,0),
    List(0,0,0),
    List(1,1,0)
  )) {
    val result = list match {
      case List(0) => "0"
      case List(x, y) => "Array: " + x + " " + y
      case _ => "Not Found"
    }
    println(result)
  }

  // method2
  val list =List(1,2,3,4,5)
  list match {
    case first :: second :: rest => println(s"first:$first, second:$second, rest:$rest")
  }

}
