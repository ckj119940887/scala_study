package chapter10

object Implicit1 extends App {
  // implicit function
  implicit def convert(num: Int): MyRichInt = new MyRichInt(num)

  println(12.myMax(15))

}

class MyRichInt(val self: Int) {
  def myMax(n: Int): Int = {
    if(n < self)
      self
    else
      n
  }

  def myMin(n: Int): Int = {
    if(n < self)
      n
    else
      self
  }
}
