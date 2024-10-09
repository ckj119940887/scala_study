package chapter10

// 上下限对传入的泛型进行限定
// class PersonList[T <: Person],上限
// class PersonList[T >: Person],下限

object Generic2 extends App {
  def test[A <: ChildG](a: A) = println(a.getClass.getName)

  // 要求的上限是Child，报错
  // test[ParentG](new ChildG)

  test[ChildG](new ChildG)
  test[SubChildG](new SubChildG)

  def test2[A >: ChildG](a: A) = println(a.getClass.getName)

  // 要求的下限是SubChildG, 报错
  // test2[SubChildG](new ParentG)

  test2[ChildG](new ChildG)
  test2[ParentG](new ParentG)
}

class ParentG {}
class ChildG extends ParentG {}
class SubChildG extends ChildG {}