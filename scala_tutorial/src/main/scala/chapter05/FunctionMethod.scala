package chapter05

object FunctionMethod {
  def main(args: Array[String]): Unit = {
    def sayHi(name: String): Unit = println("hi " + name)

    sayHi("kejun")
    FunctionMethod.sayHi("kejun")
  }

  def sayHi(name: String): Unit = println("Hi " + name)

}
