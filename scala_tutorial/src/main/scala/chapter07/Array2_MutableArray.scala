package chapter07

import scala.collection.mutable.ArrayBuffer

object Array2_MutableArray extends App{
  val arr1: ArrayBuffer[Int] = new ArrayBuffer[Int]()
  // 添加元素
  //arr1 :+ 15, 针对可变数组使用该方法无效
  //在尾部添加
  arr1 += 15
  println(arr1(0))
  val newArr1 = arr1 += 11
  // 两者是相同的，因为可变数组传的是引用，
  // 所以可变数组可以不用像不可变数组那样将结果返回到一个新的量中
  println(newArr1 == arr1)
  // 后面已经追加了11
  println(arr1)

  // 在头部添加，+=:
  50 +=: arr1
  println(arr1)

  // 对于可变数组，不推荐使用上述方法，可以直接使用方法append,prepend
  arr1.append(99)
  arr1.prepend(77)
  println(arr1)
  arr1.insert(1, 13, 66)
  println(arr1)
  arr1.insertAll(1, newArr1)
  arr1.prependAll(newArr1)

  // delete element
  arr1.remove(3) // 3 represent the position
  arr1.remove(0,3)

  // companion object
  val arr2 = ArrayBuffer(12,33)
  // 这里没有打印一个地址的原因是，这里默认调用了ArrayBuffer.toString
  println(arr2)
  println(arr2(1))
}
