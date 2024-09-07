package chapter06

object CompanionObject2 {
  def main(args: Array[String]): Unit = {
    val s = StudentY.newStudentY("ckj", 12)
    s.printInfo()

    // 这里直接调用了apply方法
    val s2 = StudentY("kkjk", 123)
    s2.printInfo()
  }
}

// 不允许直接通过类进行实例化，只能通过伴生对象进行实例化，所以使用了private,将构造方法私有化
class StudentY private(val name: String, val age: Int) {
  def printInfo(): Unit = {
    println(s"$name is $age, come from ${StudentX.school}")
  }
}

object StudentY {
  val school:String = "NEU"

  def newStudentY(name: String, age: Int): StudentY = new StudentY(name, age)

  // 不使用上面的构造方法时，将默认调用apply方法完成实例化
  def apply(name: String, age: Int): StudentY = new StudentY(name, age)
}