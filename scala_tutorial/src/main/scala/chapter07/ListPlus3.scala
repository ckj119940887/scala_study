package chapter07

object ListPlus3 extends App {
  // map 返回的是列表的列表
  val l1 = List.range(1, 5) map (
    i => List.range(1, i) map (j => (i, j))
    )
  println(l1)

  // flatMap 返回的是元素的列表
  val l2 = List.range(1, 5) flatMap (
    i => List.range(1, i) map (j => (i, j))
    )
  println(l2)

  // partition 返回的是是一对列表。其中一个包含所有前提条件为 true 的元素，另一个包含所有前提条件为 false 的元素 。
  val l3 = List(1,2,3,4) partition(_ % 2 == 0)
  println(l3)

  // find方法跟filter也很像，不过它返回满足给定前提条件的第一个元素，而不是所有元素。
  // XS find p 这个操作接收列表XS和前提条件函数p两个操作元，返回一个可选值。
  // 如果 XS 中存在一个元素 x 满足以 p(x）为 true ，那么就返回 Some(x）。
  // 而如果对于所有元素而言 p 都为 false ，那么则返回 None 。
  val l4 = List(1,2,3,4) find (_ % 2 == 0)
  println(l4)

  // takeWhile 和 dropWhile 操作符也将一个前提条件作为右操作元 。
  // xs takeWhile p 操作返回列表 XS 中连续满足 p 的最长前缀 。
  val l5 = List(1,2,3,-4,5) takeWhile(_ > 0)
  println(l5)

  // forall, 接收一个列表XS和一个前提条件p作为人参。如果列表中所有元素都满足 p 就返回 true 。
  val l6 = List(1,2,3,4,5)
  println(l6.forall(_ > 0))

  // exists, 要求是XS中存在一个元素满足前提条件 p
  println(l6.exists(_ > 3))

  // sortWith
  println(List(1, -3, 4, 2, 6) sortWith(_ < _))
}
