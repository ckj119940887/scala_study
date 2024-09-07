package chapter07

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Array3_conversion_between_mutable_immutable extends App {
  //可变数组转化为不可变数组
  val arr: ArrayBuffer[Int] = ArrayBuffer(12, 33, 45)
  val newArr: Array[Int] = arr.toArray
  println(newArr.mkString("-"))
  println(arr)

  //不可变数组转化为可变数组
  val arr2: Array[Int] = Array(1,2,3)
  val newArr2: mutable.Buffer[Int] = arr2.toBuffer
  println(newArr2)

  //
  val array: Array[Array[Int]] = Array.ofDim[Int](2,3)
  array(0)(2) = 1

  //for(i <- array.indices; j <- array(0).indices)
  for(i <- 0 until array.length; j <- 0 until array(i).length) {
    println(array(i)(j))
  }

  array.foreach(line => line.foreach(println))
  array.foreach(_.foreach(println))
}
