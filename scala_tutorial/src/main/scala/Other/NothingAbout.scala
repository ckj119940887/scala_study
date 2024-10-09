package Other

object NothingAbout extends App {
  // Nothing是任意类的子类
  // 返回类型是Nothing，表明该方法不会正常返回
  def myError(message: String): Nothing = throw new RuntimeException(message)

  // Nothing是任意类的子类，所以类型上并没有任何问题
  def divide(x: Int, y: Int) =
    if(y != 0) x/y
    else myError("can't divide by zero")
}
