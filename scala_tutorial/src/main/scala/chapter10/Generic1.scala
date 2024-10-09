package chapter10

// +T(协变) -T(逆变)
// +T(协变) Son是子类 Father是父类, MyList[Son] 是 MyList[Father] 子类
// -T(逆变) Son是子类 Father是父类, MyList[Son] 是 MyList[Father] 父类
// T(不变) Son是子类 Father是父类, MyList[Son] 和 MyList[Father] 没有任何关系

object Generic1 extends App {
  // class MyCollection1[E] {}，这里会报错，因为两者没有任何关系，不变
  // val childList1: MyCollection1[Parent] = new MyCollection1[Child]

  // class MyCollection2[-E] {}
  val childList2: MyCollection2[Child] = new MyCollection2[Parent]

  // class MyCollection3[+E] {}
  val childList3: MyCollection3[Parent] = new MyCollection3[Child]
}

class Parent {}
class Child extends Parent {}

class MyCollection1[E] {}
class MyCollection2[-E] {}
class MyCollection3[+E] {}
