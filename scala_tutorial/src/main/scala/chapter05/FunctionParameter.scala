package chapter05

object FunctionParameter {
  def main(args: Array[String]): Unit = {
    // 可变参数
    def f1(str: String*): Unit = {
      println(str)
    }

    f1("ckj", "hel")
  }
}
