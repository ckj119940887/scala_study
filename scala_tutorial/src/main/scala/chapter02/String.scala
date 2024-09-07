package chapter02

object String {
  def main(args: Array[String]) = {
    println(123 + "234")

    // *用于将字符串复制多次并拼接
    println("234" * 4)

    // printf
    printf("%d--%s\n", 123, "ckj")

    // 字符串模板,通过$获取变量值
    val age: Int = 123
    val name: String= "ckj"
    println(s"${age}--${name}")

    // 三引号表示字符串，保持多行字符串的原格式输出
    val test: String =s"""
         |ckj
         |heheda
         |""".stripMargin

    println(test)
  }
}
