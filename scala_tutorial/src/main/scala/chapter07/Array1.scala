package chapter07

object Array1 extends App {
  val arr1 = new Array[Int](10)
  println(arr1.length)

  // 使用Array伴生对象的apply方法
  val arr2 = Array(12, 33, 44, 55)
  for(elem <- arr2)
    println(elem)

  // 不可变数组指的是数组的地址和大小不能改，即不能增加和删除，但是内容可以修改
  arr2(2) = 9
  for(elem <- arr2)
    println(elem)

  for(i <- arr2.indices)
    println(arr2(i))

  // 迭代器
  val iter = arr2.iterator
  while(iter.hasNext)
    println(iter.next())

  // foreach
  arr2.foreach(println(_))

  println(arr2.mkString("--"))

  // 添加元素(返回新的数组,不是修改原数组)
  // :+是个方法
  val arr3 = arr2.:+(77)
  val arr4 = arr2.+:(39)
  arr2.foreach(println(_))
  println(arr3.mkString("--"))
  println(arr4.mkString("--"))

  // .可以省略，如果调用的符号是以 : 结尾，那么调用者在后面
  val arr5 = arr2 :+ 88
  val arr6 = 11 +: arr2
}

