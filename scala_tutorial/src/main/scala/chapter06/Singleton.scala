package chapter06

object Singleton {
  def main(args: Array[String]): Unit = {
    // 两者指向的是同一个内存空间，即单例对象
    val s = StudentZ.getInstance()
    val n = StudentZ.getInstance()
    println(s)
    println(n)
  }
}

class StudentZ private(val name: String, val age: Int) {
  def printInfo() ={
    println(s"$name is $age")
  }
}

// 饿汉式
//object StudentZ {
//  private val student = new StudentZ("ckj", 123)
//  def getInstance(): StudentZ = {
//    student
//  }
//}

// 懒汉式
object StudentZ {
  private var student:StudentZ = _
  def getInstance(): StudentZ = {
    if(student != null) {
      student
    }
    else {
      student = new StudentZ("kejun", 222)
      student
    }
  }
}
