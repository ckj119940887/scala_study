package chapter06

object EnumerationApp {
  def main(args: Array[String]): Unit = {
    println(WorkDay.MONDAY)

    for(w <- WorkDay.values)
      println(w.id)

  }
}

// 枚举类的定义
object WorkDay extends Enumeration {
  val MONDAY = Value(1, "Monday")
  val THUESDAY = Value(2, "Thuesday")
}

// 应用类,相当于自动包含main方法
object TestApp extends App {
  println("app start")
}