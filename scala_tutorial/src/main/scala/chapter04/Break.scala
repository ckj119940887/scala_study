package chapter04

import scala.util.control.Breaks

object Break {
  def main(args: Array[String]): Unit = {
    // 采用抛出异常的方法实现break
    try {
      for(i <- 1 to 4) {
        if(i == 3)
          throw new RuntimeException()
      }
    } catch {
      case e: Exception =>
    }

    // 使用Breaks类的break方法
    Breaks.breakable(
      for(i <- 1 to 5)
        if(i == 3)
          Breaks.break()
    )
  }

}
