package chapter07.Common

import scala.collection.mutable

object CommonOperation7 extends App {
  // 将集合中出现的单词进行统计，将出现频率最高的前三个筛选出来
  val stringList = List(
    "hello",
    "hello world",
    "hello scala",
    "hello spark from scala",
    "hello flink from scala"
  )

  val wordList1 = stringList.flatMap(elem => elem.split(" "))
  val mutableMapList1: mutable.Map[String, Int] = mutable.Map()
  wordList1.map(
    elem => {
      if(mutableMapList1.contains(elem))
        mutableMapList1(elem) += 1
      else
        mutableMapList1.update(elem, 1)
    }
  )
  println(mutableMapList1.toList.sortWith(_._2 > _._2).take(3))
}
