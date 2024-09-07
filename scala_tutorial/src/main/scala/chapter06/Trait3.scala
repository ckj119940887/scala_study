package chapter06

object Trait3 {
  def main(args: Array[String]): Unit = {
    // 调用的是Test2中的test()，即使是正常的类继承中(使用extends了，也还是Test2中的)
    // 这就是overlay
    val s = new PersonY() with Test1 with Test2 {
      override def test(): Unit = super.test()
    }
    s.test()
  }

}

trait Test1{
  def test(): Unit = {
    println("Test1")
  }
}

trait Test2{
  def test(): Unit = {
    println("Test2")
  }
}
