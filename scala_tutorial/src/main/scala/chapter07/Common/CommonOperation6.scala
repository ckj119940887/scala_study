package chapter07.Common

import scala.collection.mutable

object CommonOperation6 extends App {
  val map1 = Map("a" -> 1, "b" -> 3, "c" -> 6)
  val map2 = Map("a" -> 6, "b" -> 2, "c" -> 9, "d" -> 3)

  // 直接使用++, map2将会覆盖掉map1
  println(map1 ++ map2)

  // 更新规则，如果存在相同的key，则将对应的value相加
  val mutableMap1 = mutable.Map("a" -> 1, "b" -> 3, "c" -> 6)
  val mutableMap2 = mutable.Map("a" -> 6, "b" -> 2, "c" -> 9, "d" -> 3)
  val map3 = mutableMap1.foldLeft(mutableMap2)(
    (mergeMap, kv) => {
      val key = kv._1
      val value = kv._2
      mergeMap(key) = mergeMap.getOrElse(key, 0) + value
      mergeMap
    }
  )

  println(map3)
}
