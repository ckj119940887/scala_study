package chapter06

// 类和属性前面如果不加修饰词public，就是公有的，加了反而不对
// 可以加private,只在类的内部和伴生对象中可用
// private[chapter06] var age: Int = 10,只在chapter06这个包中可以被访问
class TestStudent {
  var name: String = _
  var age: Int = _
  val school: String = "NEU"

  // 辅助构造器
  def this(name: String) = {
    this() //调用主构造器
    this.name = name
  }

  def this(name: String, age: Int) = {
    this(name)
    this.age = age
  }

  def printInfo() = {
    println(s"${name} is ${age}")
  }
}

// 推荐使用这种方法
class TestStudent1(var name: String, var age: Int) {

}

class Graduate extends TestStudent {
  // 重写属性和方法都必须加override
  override val school: String = "khe"

  def this(name: String, age: Int) = {
    this()
    this.name = name
    this.age = age
  }

  override def printInfo(): Unit = {
    println("test")
  }
}

object BasicClass {
  def main(args: Array[String]): Unit = {

    val s = new TestStudent("ckj", 12)
    s.printInfo()

    val g = new Graduate()
    g.printInfo()
  }
}
