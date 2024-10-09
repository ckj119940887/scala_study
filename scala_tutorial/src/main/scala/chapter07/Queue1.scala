package chapter07

import scala.collection.immutable.Queue
import scala.collection.mutable

object Queue1 extends App {
  //可变对象
  val queue1 = new mutable.Queue[String]()
  queue1.enqueue("a", "b", "c")
  println(queue1)
  println(queue1.dequeue())
  println(queue1)

  //不可变对象
  val queue2: Queue[String] = Queue("a", "b")
  println(queue2)
}
