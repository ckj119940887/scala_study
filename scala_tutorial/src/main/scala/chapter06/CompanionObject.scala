package chapter06

object CompanionObject {
  def main(args: Array[String]): Unit = {
    val s = new StudentX("ckj", 12)
    s.printInfo()
  }
}

// 因为scala是完全的面向对象的语言，所以直接取消了static关键字，
// 但是为了与java互操作，所以支持了伴生对象，伴生类中可以直接访问
// 伴生对象中的属性和方法

class StudentX(val name: String, val age: Int) {
  def printInfo(): Unit = {
    println(s"$name is $age, come from ${StudentX.school}")
  }
}

object StudentX {
  val school:String = "NEU"
}