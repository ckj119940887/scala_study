package chapter02

import java.io.{File, PrintWriter}
import scala.io.Source

object FileIO {
  def main(args: Array[String]) = {
    // 读取数据
    Source.fromFile("src/main/resources/test.txt").foreach(print)
    // 写入数据
    val writer = new PrintWriter(new File("src/main/resources/test.txt"))
    writer.write("123456")
    writer.close()
  }
}
