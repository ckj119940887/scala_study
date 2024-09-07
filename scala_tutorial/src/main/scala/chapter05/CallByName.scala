package chapter05

import scala.annotation.tailrec

object CallByName {
  def main(args: Array[String]): Unit = {
    def f1(): Int = {
      println("f1")
      12
    }

    // 要求a的返回必须是Int,这里的a本质上是段代码
    def f2(a: => Int) = {
      println(a)
      println(a)
    }

    f2(f1())

    var n = 10
    whileSelf(n>0) {
      println(n)
      n = n - 1
    }
  }

  def whileSelf(cond: => Boolean): (=> Unit) => Unit = {
    def doLoop(op: => Unit): Unit = {
      if(cond) {
        op
        whileSelf(cond)(op)
      }
    }

    doLoop _
  }

  def whileCurry(cond: => Boolean)(op: => Unit): Unit = {
    if(cond) {
      op
      whileCurry(cond)(op)
    }
  }

}
