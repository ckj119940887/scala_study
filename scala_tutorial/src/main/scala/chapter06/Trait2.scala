package chapter06

object Trait2 {
  def main(args: Array[String]): Unit = {
    // 动态混入
    val s = new PersonY() with Talent {
      override def singing(): Unit = println("student is good at singing")

      override def dancing(): Unit = println("student is good at dancing")
    }
    s.sayHello()
    s.date()
    s.play()
  }
}

trait Talent {
  def singing(): Unit
  def dancing(): Unit
}