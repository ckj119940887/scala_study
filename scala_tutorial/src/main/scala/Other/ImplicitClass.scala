package Other

/*
你可能会觉得任何类定义前面都可以放implicit。并非如此，隐式类不能是样例类，并且其构造方法必须有且仅有一
个参数。不仅如此，隐式类必须存在于另 一个对象、类或特质里面。在实际使用中，只要是用隐式类作为富包装类来给某个已有的类添加方法，这些限制应
该都不是问题。
* */

// 隐式类，编译器会生成一个从类的构造方法参数到类本身的隐式转换 。
case class Rectangle(width: Int, height: Int)

object ImplicitClass extends App {
  // 隐式类还会自动生成如下代码：
  /* implicit def RectangleMaker(width: Int) =
       new RectangleMaker(width)
  */
  implicit class RectangleMaker(width: Int) {
    def x(height: Int) = new Rectangle(width, height)
  }
}
