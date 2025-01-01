package Other

/*
编译器会插人隐式定义的最后一个地方是参数列表。编译器有时候会将 someCall(a）替换为 someCall(a)(b），
或者将 new Some(a）替换成 new Some(a)(b），通过追加一个参数列表的方式来完成某个函数调用。隐式参数
提供的是整个最后一组柯里化的参数列表，而不仅仅是最后一个参数。举例来说，如果 someCall 缺失的最后一个
参数列表接收三个参数，那么编译器会将 someCall(a）替换成 someCall(a)(b), 插入的标识符，比如（b,c,d）。
就这个用法而言，不仅仅是被插入的标识符。b 、c 、d 需要在定义时标记为 implicit, someCall 或 someClass
的定义中最后一个参数列表也得标记为 implicit 。
*/

object ImplicitParameter extends App {
  class PreferredPrompt(val preference: String)
  class PreferredDrink(val preference: String)
  object Greeter {
    def greet(name: String)(implicit prompt: PreferredPrompt,
                            drink: PreferredDrink) = {
      println (" Welcome, " + name + ". The system is ready.")
      print (" But while you work ,")
      println (" why not enjoy a cup of " + drink.preference + "?")
      println(prompt.preference)
    }
  }
  object JoesPrefs {
    implicit val prompt = new PreferredPrompt (" Yes, master > ")
    implicit val drink = new PreferredDrink("tea")
  }
}
