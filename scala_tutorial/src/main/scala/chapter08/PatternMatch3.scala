package chapter08

class Student(val name:String, val age:Int)
object Student {
  def apply(name: String, age:Int) = new Student(name, age)

  //必须实现一个unapply方法，用来对对象属性进行拆解
  def unapply(student: Student): Option[(String, Int)] = {
    if(student == null)
      None
    else
      Some(student.name, student.age)
  }
}

object PatternMatch3 extends App {
  val student = Student("ckj", 11)

  val result = student match {
    case Student("ckj", 11) => "ckj, 18"
    case _ => "Else"
  }

  println(result)
}
