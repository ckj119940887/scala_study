package Other

// 参数传入时，先求值，在初始化相应的成员
class Rational(val num: Int, val den: Int)

// 子类中定义的val成员则在类对象初始化以后再求值
trait RationalTrait {
  val numerArg: Int
  val denomArg: Int
  require(denomArg != 0)
  private val g = gcd(numerArg, denomArg)
  val numer = numerArg / g
  val denom = denomArg / g
  private def gcd(a: Int, b: Int): Int = if(b == 0) a else gcd(b, a % b)
  override def toString: String = numer + "/" + denom
}

// 对字段的初始化延长到第一次使用的时候
trait LazyRationalTrait {
  val numerArg: Int
  val denomArg: Int
  lazy val numer = numerArg / g
  lazy val denom = denomArg / g
  override def toString: String = numer + "/" + denom
  private lazy val g = {
    require(denomArg != 0)
    gcd(numerArg, denomArg)
  }
  private def gcd(a: Int, b: Int): Int = if(b == 0) a else gcd(b, a % b)
}

object LazyMember extends App {
  // 此时会报错，require处报错，因为还没求值，所以使用的时默认值0
  //匿名类是在 RationalTrai t 特质之后被初始化的 。 因此，在
  //Rational Trait 的初始化过程中， numerArg 和 denomArg 的值并不可用
//  new RationalTrait {
//    val numerArg = 1
//    val denomArg = 2
//  }

  // 解决该问题的两种方法：1.预初始化字段 2.lazy
  // 初始化的代码段出现在超特质 Rational Trait 之前，以 with 隔开
  new {
    val numerArg = 1
    val denomArg = 2
  } with RationalTrait

  println(
    new LazyRationalTrait {
      override val numerArg: Int = 1
      override val denomArg: Int = 2
    }.toString
  )

}

