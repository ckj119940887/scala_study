package chapter01

class Companion_Object(name: String, age: Int) {
  def printInfo(): Unit = {
    println(name + " " + age + " " + Companion_Object.school)
  }
}

// 伴生对象
object Companion_Object {
  // 这里的school相当于static变量，在类中可以直接引用该量
  val school: String = "US"

  def main(args: Array[String]): Unit = {
    val alice = new Companion_Object("kejun", 21)
    alice.printInfo()
  }
}