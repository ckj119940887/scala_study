package Other

/*
类名与参数列表之间的private用来表明构造方法是私有的，即无法直接通过 new Queue(List(1, 2), List(3)) 来构造
但是还是要提供构造函数，这里有两种方法：1）提供辅助构造方法；2）伴生对象
 */
class Queue[T] private (private val leading: List[T], private val trailing: List[T]) {
  // 辅助构造方法
  def this() = this(Nil, Nil)
  // 使用可变参数，来支持Queue(1,2,3)这样的创建方式
  def this(elems: T*) = this(elems.toList, Nil)
}

object Queue {
  def apply[T](xs: T*) = new Queue[T](xs.toList, Nil)
}

// 隐藏类的另一种更加激进的方法是隐藏类的本身，定义一个特质，将方法在子类中进行实现
trait Queue2[T] {
  def head: T
  def tail: Queue2[T]
  def enqueue(x: T): Queue2[T]
}

object Queue2 {
  def apply[T](xs: T*): Queue2[T] = new QueueIml[T](xs.toList, Nil)

  private class QueueIml[T](private val leading: List[T], private val trailing: List[T]) extends Queue2[T] {
    override def head: T = {leading.head}
    override def tail: QueueIml[T] = new QueueIml(leading.tail, trailing)
    override def enqueue(x: T): Queue2[T] = new QueueIml(Nil, Nil)
  }
}

object TypeParameterize extends App {

}
