package Other

/*
Scala由一个名为 Option 的标准类型来表示可选值。这样的值可以有两种
形式：Some(x），其中 x 是那个实际的值；或者None对象，代表没有值 。
*/
object OptionStudy extends App {
  val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
  }

  println(capitals get "China")
  println(show(capitals get "China"))
}
