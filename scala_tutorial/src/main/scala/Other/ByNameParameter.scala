package Other

object ByNameParameter extends App {
  val assertionEnabled = false

  // 该方法传参数的时候很复杂，myAssert(() => 5/0)
  def myAssert(predicate: () => Boolean) = {
    if(assertionEnabled && !predicate())
      throw new AssertionError()
  }

  // 传名参数，与直接传入bool变量的区别是传名参数在调用 byNameAssert之前不会对表达式求值
  // 所以传入 5/0时的结果是不同的，同时传入的表达式更加简洁
  def byNameAssert(predicate: => Boolean) = {
    if(assertionEnabled && !predicate)
      throw new AssertionError()
  }

  // 直接传入bool表达式，会优先对传入的表达式求值
  def boolAssert(predicate: Boolean) = {
    if(assertionEnabled && !predicate)
      throw new AssertionError()
  }

  // 当assertionEnabled被diable时，下面两种调用的结果不同，boolAssert会直接报异常，byNameAssert则不会
  byNameAssert(5/0 == 0)
  boolAssert(5/0 == 0)
}
