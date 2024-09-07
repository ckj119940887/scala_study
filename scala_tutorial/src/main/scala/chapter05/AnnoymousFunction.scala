package chapter05

object AnnoymousFunction {
  def main(args: Array[String]): Unit = {
    // 匿名函数
    val fun = (name: String) => println(name)

    fun("ckj")

    def f(func: String => Unit) = func("test")

    f(fun)

    // 匿名函数简化
    f(name => println(name))

    println(multipleParameter(add))
    println(multipleParameter(mul))
    println(multipleParameter(_ + _))
    println(multipleParameter((a,b) => a + b))
  }

  val add = (a: Int, b: Int) => a + b
  val mul = (a: Int, b: Int) => a * b

  def multipleParameter(func: (Int, Int) => Int): Int = func(1,2)


}
