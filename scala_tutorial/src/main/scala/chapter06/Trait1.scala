package chapter06

object Trait1 {
  def main(args: Array[String]): Unit = {
    val s = new PersonY()
    s.sayHello()
    s.date()
    s.play()
  }
}

class PersonX {
  val name: String = "person"
  val age: Int = 19
  def sayHello() = {
    println("hello from " + name)
  }
}

// trait中即可以有抽象属性和方法，也可以有具体的属性和方法
// 一个类可以混入多个特质，这类似于java中的抽象类
// trait可以替代java接口，也是对单继承的一种补充
trait Young {
  val age: Int = 0
  val name: String = "young"

  // 抽象方法
  def date(): Unit

  def play() = {
    println("young is playing")
  }
}

class PersonY extends PersonX with Young {
  override val name: String = "student"
  override val age: Int = 11
  override def date(): Unit = {
    println("student is dating")
  }

  def study(): Unit = {
    println(s"$name is studying")
  }

  override def sayHello(): Unit = {
    super.sayHello()
    println(s"hello from student $name")
  }
}
