package chapter07.Common

import scala.collection.mutable

object CommonOperation8 extends App {
  val stringList: List[(String, Int)] = List(
    ("hello", 1),
    ("hello world", 2),
    ("hello scala", 3),
    ("hello spark from scala", 1),
    ("hello flink from scala", 2)
  )

  /*
  val mutableMap: mutable.Map[String, Int] = mutable.Map()
  for(elem <- stringList) {
    if(mutableMap.contains(elem._1))
      mutableMap(elem._1) += 1
    else
      mutableMap.update(elem._1, elem._2)
  }

  val mutableCountMap: mutable.Map[String, Int] = mutable.Map()
  for(elem <- mutableMap) {
    val targetString = elem._1.split(" ")
    val times = elem._2
    targetString.map( elem =>
      if(mutableCountMap.contains(elem))
        mutableCountMap(elem) += times
      else
        mutableCountMap.update(elem, times)
    )
  }
  println(mutableCountMap.toList.sortWith(_._2 > _._2).take(3))
   */

  val preCountList = stringList.flatMap(
    tuple => {
      val strings = tuple._1.split(" ")
      strings.map(word => (word, tuple._2))
    }
  )

  val preCountMap = preCountList.groupBy(_._1)

  val countMap = preCountMap.mapValues(
    tupleList => tupleList.map(_._2).sum
  )
  println(countMap.toList.sortWith(_._2 > _._2).take(3))
}
