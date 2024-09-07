package chapter06

object BasicAbstractClass {
  def main(args: Array[String]) = {
    val student = new Student
    student.eat()
    student.sleep()
  }
}

// 没有初始化的属性就是抽象属性,子类对抽象属性进行实现，可以用var修饰符。子类对非抽象属性重写，父类非抽象属性只支持val类型，不支持var。
// 没有实现的方法就是抽象方法
abstract class Person {
  val name: String = "person"
  var age: Int

  def eat(): Unit = {
    println("person eat")
  }

  def sleep(): Unit
}

class Student extends Person {
  override val name: String = "student"
  var age: Int = 0

  override def eat(): Unit = {
    super.eat()
    println("student eat")
  }

  override def sleep(): Unit = {
    println("student sleep")
  }
}
