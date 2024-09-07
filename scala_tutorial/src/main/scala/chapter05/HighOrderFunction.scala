package chapter05

object HighOrderFunction {
  def main(args: Array[String]): Unit = {
    def f(n: Int): Int = {
      n + 1
    }

    println(f(23))

    // function as parameter
    val f1: Int => Int = f
    println(f1(22))

    val f2 = f _
    println(f2(11))

    val f3: Int => Int = _  + 1
    println(f3(10))

    def f4(op: (Int, Int) => Int, a: Int, b: Int): Int = op(a,b)
    println(f4((_ + _), 1, 2))


    // function as return value
    def f5(): (Int, Int) => Int = _ + _
    println(f5())
    println(f5()(4, 99))

    val add: Int => Int = _ + 1
    //val res = processArray(Array(1,2,3,4), add)
    val res = processArray(Array(1,2,3,4), a => a + 1)
    printArray(res)
  }

  def processArray(arr: Array[Int], op: Int => Int): Array[Int] = {
    for(elem <- arr) yield op(elem)
  }

  def printArray(arr: Array[Int]): Unit = {
    for (elem <- arr)
      println(elem)
  }

}
