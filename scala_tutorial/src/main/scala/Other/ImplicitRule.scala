package Other

/*
标记规则：只有标记为 implicit 的定义才可用。关键字 implicit 用来
标记哪些声明可以被编译器用作隐式定义。可以用 implicit 来标记任何变量、
函数或对象定义 。

作用域规则：被插入的隐式转换必须是当前作用域的单个标识符，或者跟隐式转换的源类型或目标类型有关联 。
Scala 编译器只会考虑那些在作用域内的隐式转换。因此，必须以某种方式将隐式转换定义引人到当前作用域才能使
得它们可用。不仅如此，除了一个例外，隐式转换在当前作用域必须是单个标识符（single identifier）。
编译器不会插入 someVariable.convert 这种形式的转换。例如，它并不会将 x + y 展开成 someVariable.convert(x)+y。
如果想让someVariable.convert 能当作隐式转换使用，需要引人它，成为单个标识符 。
一旦引人成单个标识符，编译器就可以自由地像 convert (x) + y 来应用它 。
事实上，对于类库而言，常见的做法是提供一个包含了一些有用的隐式转换的 Preamble 对象 。
这样使用这个类库的代码就可以通过一个“ import Preamble._”来访问该类库的隐式转换 。

这个“单标识符”规则有一个例外。编译器还会在隐式转换的源类型或目标类型的伴生对象中查找隐式定义 。

每次一个规则：每次只能有一个隐式定义被插人 。编译器绝不会将 x + y 重写为convertl(convert2(x)) + y 。
不过，可以通过让隐式定义包含隐式参数的方式绕过这个限制。

显式优先原则：只要代码按编 写 的样子能通过类型检查，就不尝试隐式定义 。
编译器不会对已经可以工作的代码做修改。这个规则必然得出这样的结论：我们总是可以将隐式标识符替换成显式的，代码会更长但更同时歧义更少 。
*/

/*
隐式转换可能用到的地方：
1.隐式转换到一个预期的类型: 规则很简单，每当编译器看见－个 X 而它需要一个 Y 的时候，它就会查找一个能将 X 转换成 Y 的隐式转换 。

2.转换接收端: 隐式转换还能应用于方法调用的接收端，也就是方法被调用的那个对象。这种隐式转换有两个主要用途。
首先，接收端转换允许我们更平滑地将新类集成到己有的类继承关系图谱当中。
其次，它们支持在语言中编写（原生的）领域特定语言（ DSL ）。
我们来看看它的工作原理，假定你写下了 obj.dolt ，而 obj 并没有一个名为 dolt 的成员 。
编译器会在放弃之前尝试插入转换。在本例中，这个转换需要应用于接收端，也就是 obj。
编译器会装作 obj 的预期“类型”为“拥有名为 dolt 的成员” 。 这个“拥有名为 dolt 的成员”类型并不是一个普通的
Scala 类型，不过从概念上讲它是存在的，这也是为什么编译器会选择在这情况下插入一个隐式转换 。

* */

object ImplicitRule extends App {
  //隐式转换到一个预期的类型
  implicit def doubleToInt(x: Double): Int = x.toInt
  val i: Int = 2.5 // 该行不会报错了

  //转换接收端
  /*
  class Rational(n: I nt , d: Int) {
    def + (that: Rational): Rational = .
    def + (that: Int): Rational= .
  }
  Rational 类有两个重载的 ＋ 方法变种，分别接收 Rational 和 Int 作为参数。因此可以对两个有理数做加法，或者对一个有理数和一个整数相加.

  scala> val oneHalf = new Rational(1 ,2)
  oneHalf: Rational = 1/2
  scala> oneHalf + oneHalf
  resO: Rational = 1/1

  scala> oneHalf + 1
  Int 虽然有多个＋方法但没有一个是接收 Rational 参数的，因此类型检查失败。
  接下来，编译器会查找一个从 Int 到另一个拥有可以应用 Rational 参数的＋方法的类型的隐式转换。
  scala> implicit def intToRational(x: Int) = new Rational(x, 1)
  */
}
