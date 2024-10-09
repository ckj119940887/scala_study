package Other

/*
每当我们编写一个模式匹配时,都需要确保完整地覆盖了所有可能的case。有时候可以通过在末尾添加一个缺省
case 来做到，不过这仅限于有合理兜底的场合。如果没有这样的缺省行为，我们如何确信自己覆盖了所有的场
景呢？

解决这个问题的手段是将这些样例类的超类标记为密封（ sealed ）的 。密封类除了在同一个文件中定义的子
类之外，不能添加新的子类 。 这一点对于模式匹配而言十分有用，因为这样一来我们就只需要关心那些已知的
样例类。不仅如此，我们还因此获得了更好的编译器支持 。 如果我们对继承自密封类的样例类做匹配，编译器
会用警告消息标示出缺失的模式组合 。
 */

sealed abstract class Expr

case class Var(name: String) extends Expr
case class Number(num: Int) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr

// raise warning to instruct you add the other case class
object SealClass extends App{
  def describe(e: Expr):Unit = e match {
    case Number(_) => println("number")
    case Var(_) => println("var")
    case _ => println("other")
  }
}
