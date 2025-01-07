# Trait 
```
每当特质中拥有一些代码，该特质则被称为 mixin(混入、混合)。

trait Alarm {
  def trigger(): String
}
```

## 特质
### 混入带有变量的特质
```
trait Notifier {
  val notificationMessage: String

  def printNotification(): Unit = {
    println(notificationMessage)
  }

  def clear(): Unit
}

这个Notifier特质拥有一个已实现的方法，而notificationMessage的值和clear则需要由混入它的类来提供实现。
此外，特质可以要求一个类必须拥有一个指定的变量，这类似于其他语言中的抽象类。

class NotifierImpl(val notificationMessage:String) extends Notifier {
  override def clear(): Unit = println("cleared")
}

将 Notifier 混入到 NotifierImpl,这里有个问题需要注意，就是必须要给出 notificationMessage 的定义，
两种方法：一种是在参数列表中给出但是必须要带关键字val；另一种是在class中额外声明。
```

### 特质作为类
```
特质同样可以以类的视角来看待。这种情况下，他们需要实现其所有的方法，同时仅拥有一个不接收任何参数的构造器。

trait Beeper {
  def beep(times: Int): Unit = {
    assert(times >= 0)
    1 to times foreach(i => println(s"Beep number: $i"))
  }
}

现在我们实际上可以直接实例化Beeper并调用其方法。比如下面的例子：
object BeeperRunner extends App{
  val TIMES = 10

  val beeper = new Beeper{}        // <= 实例化一个特质
  beeper.beep(TIMES)
}
```

### 混入组合
```
abstract class Connector {
  def connect()
  def close()
}

trait ConnectorWithHelper extends Connector {
  def findDriver(): Unit = {
    println("Find driver called.")
  }
}

class Watch(brand:String, initialTime: Long){
  def getTime(): Long = System.currentTimeMillis() - initialTime
}

object Test extends App {
  val reallyExpensiveWatch = new Watch("really expensive brand", 1000L) with ConnectorWithHelper {
    override def connect(): Unit = println("Connected with another connector.")
    override def close(): Unit = println("Closed with another connector.")
  }

  reallyExpensiveWatch.findDriver()
  reallyExpensiveWatch.connect()
  reallyExpensiveWatch.close()
}
```

```
上述代码在编译的时候会出现如下错误：

illegal inheritance; superclass Watch
 is not a subclass of the superclass Connector
 of the mixin trait ConnectorWithHelper
  val reallyExpensiveWatch = new Watch("really expensive brand", 1000L) with ConnectorWithHelper {

该错误消息告诉我们，由于ConnectorWithHelper特质已经扩展了Connector类，所有使用该特质进行组合的类必须是Connector的子类。
现在让我们假如需要混入另一个同样已经扩展了一个类的特质，但被扩展的这个类与之前不同。
根据之前的逻辑，会需要Watch同样需要是该类的子类。但这是不可能出现的，因为我们同时只能扩展一个类，
这也就是 Scala 如何来限制多重继承以避免危险错误的发生。

如果我们想要修复这个例子的编译错误，我们不得不去修改原有的Watch类的实现以确保其是Connector的子类。
然而这可能并非我们所原本期望的，或许这种情况下需要一些重构。
```

### 组合自类型(self-type) 
```
我们看到了如何在Watch类中扩展Connector以便能够编译我们的代码。
有些场景中我们或许真的需要强制一个类来混入一个特质，或者同时有其他的特质或多个特质需要混入。

trait AlarmNotifier {
  this: Notifier =>

  def trigger(): String
}

这里我们展示了什么是自类型。
第二行代码将Notifier的所有方法引入到了新特质的当前作用域，它同时要求所有混入了该特质的类必须同时混入Notifier。
否则将会出现编译错误。如果使用self来代替this，我们则可以使用手动的方式来在AlarmNotifier中引用Notifier的方法，比如self.printNotification()。

下面的代码展示了如何来使用这个新的特质：
object SelfTypeWatchUser extends App {
  AlarmNotifier {
    val watch = new Watch("alarm with notification", 1000l) with AlarmNotifier with Notifier {
      override def trigger():String = "Alarm triggered."
      override def clear(): Unit = println("Alarm cleared.")
      override val notificationMessage:String = "The notification."
    }
  }
  println(watch.trigger())
  watch.printNotification()
  println(s"The time is ${watch.getTime()}.")
  watch.clear()
}

如果在上面的代码中去掉同时扩展Notifier的部分则会出现编译错误。
```

## 相同签名和不同返回类型
### 相同签名和返回类型的混入
```
trait A {
  def hello(): String = "Hello, I am trait A!"
}

trait B {
  def hello(): String = "Hello, I am trait B!"
}

object Clashing extends App with A with B {
  println(hello())
}

我们将得到如下错误：
object Clashing inherits conflicting members:
  method hello in trait A of type ()String  and
  method hello in trait B of type ()String
(Note: this can be resolved by declaring an override in object Clashing.)
object Clashing extends App with A with B {

方法冲突在多重继承中是一个问题，但是和你看到的一样，我们致力于选择一个可用的方法。
override def hello():String = super[A].hello()

如果处于某些原因我们相同时使用两个方法呢？
这种情况下，我们可以创建另外一个名字不同的方法来调用另一个指定特质中的方法。
我们同样可以直接通过super符号直接引用这些方法而不是将他们包装在另一个方法中。
然而我个人更倾向于包装在另一个方法内，否则代码将会变得很乱。

如果在上面的例子中，我们直接使用override def hello(): String = super.hello()而不是super[A].hello()，
真正被选择的又是那个特质中的方法呢？这种情况下将会选择 B 中的方法。这取决于 Scala 中的线性化特性
```

### 相同签名和不同返回类型的混入
```
如果方法的传入参数在类型或数量上有所不同则上面的问题就不再存在，因为这成了一个新的签名。

trait A {
  def hello(): Int = 0
}

trait B {
  def hello(): String = "Hello, I am trait B!"
}

object Clashing extends App {
  override def hello(): String = super[B].hello()
  override def hello(): Int= super[A].hello()
}

这展示了 Scala 会避免我们在多重继承中进行这样危险的操作。
这里重写一个还是同时重写两个都会报错，这里只能牺牲trait。

object Clashing extends App {
  val a = new A {}
  val b = new B {}
  println(a.hello())
  println(b.hello())
}
```

## 多重继承
```
Scala 中的多重继承由特质实现并遵循线性化规则。

在多重继承中，如果一个特质已经显式扩展了一个类，则混入该特质的类必须是之前特质混入的类的子类。
这意味着当混入一个已扩展了别的类的特质时，他们必须拥有相同的父类。

如果特质中定义或声明了相同签名但返回类型不同的方法，则无法混入这些特质。

需要特别小心的是特质中定义了相同签名和返回类型的方法。
若果方式仅是声明而被要求实现，这不会带来问题而且只需要提供一个实现即可。
```

## 测试特质
### 使用一个类
```
让我们看一下前面见到的DoubledMultiplierIdentity将会被如何测试。
一种方式是将这个特质混入一个测试类来测试它的方法：

class DoubledMultiplierIdentityTest extends Flatspec 
                                    with ShouldMatchers     
                                    with DoubledMultiplierIdentity
                                    
然而这会编译失败并显示一个错误信息：
Error:(5, 79) illegal inheritance; superclass FlatSpec 
  is not a subclass of the superclass MultiplierIdentity 
  of the mixin trait DoubledMultiplierIdentity 
class DoubledMultiplierIdentityTest extends FlatSpec with ShouldMatchers 
with DoubledMultiplierIdentity { 

事实上特质只能被混入到一个与该特质拥有相同基类的类。
这意味着为了测试这个特质，我们需要在我们的测试类中创建一个虚拟的类然后再使用它：

import org.scalatest.{ ShouldMatchers, FlatSpec}

class DoubledMultiplierIdentityTest extends FlatSpec with ShouldMatchers {

  class DoubledMultipliersIdentityClass extends DoubledMultiplierIdentity

  val instance = new DoubledMultiplierIdentityClass

  "identity" should "return 2 * 1" in {
    instance.identity should equals(2)
  }
}
```

### 混入特质
```
我们可以将特质混入来对他进行测试。有几个地方我们可以这么做：混入到一个测试类或者一个单独的测试用例。

什么是混入特质：
class Original {
  def hello(): Unit = println("I am Original")
}

trait A extends Original {
  override def hello(): Unit = println("I am A")
}

trait B extends Original {
  override def hello(): Unit = println("I am B")
}

class BaseClass

class N extends BaseClass with A

object Test extends App {
  val n = new N with A
  n.hello()
}
```

```
如上面的代码所示，如果是
class N extends A，这不是混入特质，这是继承，所以可以编译，并且结果是对的。A已经继承了Original，所以N也继承了Original。
而如果是
class N extends BaseClass with A, 这是混入特质，所以需要被混入的类或特质继承自相同的基类。
```

#### 混入到测试类
```
只有当该特质确定没有扩展任何其他类时，才可以将该特质混入到一个测试类，因此特质、测试类的超类必须是相同的。
除了这样，其他任何方式都和我们前面做的一样。

trait A {
  def hello(): String = "Hello, I am trait A!"
  def pass(a: Int): String = s"Trait A said: 'You passed $a.'"
}

import org.scalatest.{ShouldMatchers, FlatSpec}

class TraitTest extends FlatSpec with ShouldMatchers with A {
  "hello" should "greet properly." in {
     hello() should equal("Hello, I am trait A！")
   }

  "pass" should "return the right string with the number." in {
      pass(10) should equal("Trait A said: 'You passed 10.'") 
   }

  it should "be correct also for negative values." in {
    pass(-10) should equal("Trait A said: 'You passed -10.'") 
  }
}
```

#### 混入到测试案例
```
我们同样可以将特质分别混入到个别测试案例中。

import org.scalatest.{ ShouldMatchers, FlatSpec }

class TraitCaseScopeTest extends FlatSpec with ShouldMatchers {
  "hello" should "greet properly." in new  A {
    hello() should equal("hello, I am trait A!")
  }
  "pass" should "return the right string with the number." in new A {
    pass(10) should equal("Trait A said: 'You passed 10.'")
  } 
  it should "be correct also for negative values." in new A {
    pass(-10) should equal("Trait A said: 'You passed -10.'")
  }
}

在上面的代码中你可以看到，这些测试用例与前面的例子一样。
但是是在单独的用例中混入 A。
这支持我们对不同的用例设置自定义，比如一个特质需要一个方法的实现或者一个变量的初始化。
这种方式也可以让我们仅专注于要测试的特质，而不需要创建它的一些实际的实例。
```
