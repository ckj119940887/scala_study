package chapter01

object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello World")

    val age = 18
    val name = "ckj"
    val weight = 99.11

    println(s"name is ${name}, age is ${age}")
    println(f"weight is ${weight}%.2f")
  }
}
