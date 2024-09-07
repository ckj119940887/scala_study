package chapter02

object Unit_Null_Nothing {
  def main(args: Array[String]): Unit = {
    // Unit 无值，只有一个实例，用于函数返回值，空值。
    def m1(): Unit = println("m1 is called")
    val a = m1()
    println(a)

    // Null 只有一个实例null，空引用。
    //var n: Int = null

    // Nothing 确定没有正常的返回值，可以用Nothing来指定返回值类型。
    // 直接抛异常了，获取不到它的值，所以时Nothing
    def m2(n: Int): Int = {
      if (n==0)
        throw new NullPointerException()
      else
        return n
    }

    val b = m2(1)
    println(b)
  }
}
