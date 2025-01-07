# 抽象类型
```
trait ContainerAT{
  type T
  val data:T

  def conpare(other:T) = data.equals(other)
}

class StringContainer(val data:String) extends ContainerAT {
  override type T = String
}

object AbstractTypesExample extends App{
  val stringContainer = new StringContainer("some text")
  println(s"Comparing with string: ${stringContainer.compare("some text")}")
}
```

# 泛型与抽象类型
## Abstract type demo
```
abstract class PrintData
abstract class PrintMaterial
abstract class PrintMedia
trait Printer{
  type Data <: PrintData
  type Material <: PrintMaterial
  type Media <: PrintMedia

  def print(data:Data, material:Material, media: Media) =
    s"Printing $data with $material material on $media media."
}

case class Paper() extends PrintMedia
case class Air() extends PrintMedia
case class Text() extends PrintData
case class Model() extends PrintData
case class Toner() extends PrintMaterial
case class Plastic() extends PrintMaterial

class LaserPrinter extends Printer {
  type Media = Paper
  type Data = Text
  type Material = Toner
}

class ThreeDPrinter extends Printer {
  type Media = Air
  type Data = Model
  type Material = Plastic
}

object Test extends App {
  val laser = new LaserPrinter
  val threeD = new ThreeDPrinter
  println(laser.print(Text(), Toner(), Paper()))
  println(threeD.print(Model(), Plastic(), Air()))
}
```

## Generic demo
```
trait GenericPrinter[Data <:PrintData, Material <: PrintMaterial, Media <: PrintMedia] {
  def print(data: Data, material: Material, media: Media) =
    s"Printing $data with $material material on $media media."
}

class GenericLaserPrinter[Data <: Text, Material <: Toner, Media <: Paper] 
  extends GenericPrinter[Data, Material, Media]

class GenericThreeDPrinter[Data <: Model, Material <: Plastic, Media <: Air] 
  extends GenericPrinter[Data, Material, Media]

另一方面，使用泛型能够允许我们复用GenericPrinter而不必为不同的打印机表示进行显式的子类化。
然而这也存在逻辑错误的风险：
class GenericPrinterImpl[Data <: PrintData, Material <: PrintMaterial, Media <: PrintMedia] 
  extends GenericPrinter[Data, Material, Media]
```

## 泛型和抽象类型的简单比较
```
泛型：
如果你仅需要类型实例化。一个好的示范是标准的集合类。
如果你正在创建一族类型。
抽象类：
如果你想允许别人能够通过特质混入类型。
如果你需要在一些允许互换的场景拥有更好的可读性。
如果你想在客户端代码中隐藏类型定义。
```

# 专用(ad-hoc)多态
```
我们创建了一个Adder特质可以对不同的类型求和。
让我们一步一步定义一个更加精密的例子，期望这样有助于连接它是如何工作的。
我们的目标是让sum方法可以对任何类别的类型(之前是数字类型)求和：
```

```
trait Adder[T] {
  def sum(a: T, b: T): T
}

object Adder {
  def sum[T: Adder](a: T, b: T): T = implicitly[Adder[T]].sum(a, b)
}

object Test extends App {
  import Adder._
  println(s"The sum of 1 + 2 is ${sum(1, 2)}")
  println(s"The sum of abc + def is ${sum("abc", "def")}")
}

上述代码会报如下错误：
could not find implicit value for evidence parameter of type DesignPatternInScala.Adder[Int]
  println(s"The sum of 1 + 2 is ${sum(1, 2)}")

这表示我们的代码不知道如何将整数或字符串隐式转换为Adder[Int]或Adder[String]。
我们需要做的是定义这些转换以告诉程序sum方法该如何做。
```

```
trait Adder[T] {
  def sum(a: T, b: T): T
}

object Adder {
  def sum[T: Adder](a: T, b: T): T = implicitly[Adder[T]].sum(a, b)
  implicit val int2Adder:Adder[Int] = new Adder[Int] {
    override def sum(a:Int, b:Int):Int = a + b
  }

  implicit val string2Adder:Adder[String] = new Adder[String] {
    override def sum(a: String, b: String):String  = s"$a concatenated with $b"
  }
}

object Test extends App {
  import Adder._
  println(s"The sum of 1 + 2 is ${sum(1, 2)}")
  println(s"The sum of abc + def is ${sum("abc", "def")}")
}
```

# 自类型
```
自类型支持我们在应用中更简便的拆分代码，然后再在其他地方指定那些需要的部分。
例子会让一切变得清晰，因此让我们看一个实例。
假如我们想要往数据库持久化信息：

//persist方法会对数据做一些转换并存储到我们的数据库。
trait Persister[T] {
  def persist(data:T)
}

trait Database[T] {
  def save(data: T)
}

trait MemoryDatabase[T] extends Database[T] {
  val db:mutable.MutableList[T] = mutable.MutableList.empty
  override def save(data:T):Unit = {
    println("Saving to in memory database.")
    db.+=:(data)
  }
}

trait FileDatabase[T] extends Database[T] {
  override def save(data:T):Unit = {
    println("Saving to file.")
  }
}
```

```
我们拥有一个特质及一些具体的数据库实现。
那么如何把数据库传递给Persister呢？它应该能够调用数据库中的save方法。可能会有以下几种方式：

1.在Persister中扩展Database。
这样虽然可行，但是会让Persister也变成了Database的实例，我们并不希望这样。
如果我们让Persister扩展Database，这意味着Persister本省也变成了一个Database(is-a 关系)。
然而这是不正确的。这里它只是使用一个数据库来实现其功能，而不能称为一个数据库。
继承将子类暴露给父级的实现细节。然而并非总是期望得到这样的结果。
开发者总是应该优先使用组合，而不是继承。
2.在Persister中拥有一个Database变量，然后使用它。
3.使用自类型。
```

```
为了能够观察自类型是如何工作的，因此使用自类型的方式。
我们的Persister接口将会变成下面这样：

trait Persister[T] { this: Database[T] =>
  def persist(data:T):Unit = {
    println("Calling persist.") 
    save(data)
  }
}

在上面的代码中，我们使用this: Database[T] =>语句将自类型包括进来。
这样支持我们像使用自身的方法一样直接使用被引入类型的方法。
另一种替代的方式是使用self: Database[T] =>。
有很多例子中使用了后面的方式，这样可以避免当我们需要在嵌套的特质或类定义中使用this而引起的混乱。
然而这种方式需要在调用被注入的方法时使用self来引用。

自类型会要求任何混入Persister类同时混入Database，否则编译将会失败。
class FilePersister[T] extends Persister[T] with FileDatabase[T]
class MemoryPersister[T] extends Persister[T] with MemoryDatabase[T]
```

## 使用多个组件
```
trait History {
  def add():Unit = {
    println("Action added to history.")
  }
}

trait Persister[T] { this: Database[T] with History =>
  def persist(data:T):Unit = {
    println("Calling persist.")
    save(data)
    add()
  }
}

我们可以通过with关键字同时添加多个需求。
然而，如果我们仅让代码做出这些改变，它并不会编译成功。
原因是现在我们必须同时混入History到Persister中：

class FilePersister[T] extends Persister[T] with FileDatabase[T] with History
class MemoryPersister[T] extends Persister[T] with MemoryDatabase[T] with History
```

### 组件冲突
```
在上面的例子中，我们拥有一个对History特质的需要，它拥有一个add方法。
如果不同组件中的方法拥有相同的签名会怎样呢？让我们试一下：

trait Mystery {
  def add(): Unit = {
    println("Mystery added!")
  }
}

trait Persister[T] { this:Database[T] with History with Mystery =>
  def persist(data:T):Unit = {
    println("Calling persist.")
    save(data)
    add()
  }
}

class FilePersister[T] extends Persister[T] with FileDatabase[T] with History with Mystery 
class MemoryPersister[T] extends Persister[T] with MemoryDatabase[T] with History with Mystery

得到如下错误信息：
Error:(47, 7) class FilePersister inherits conflicting members:
    method add in trait History of type ()Unit and
    method add in trait Mystery of type ()Unit
(Note: this can be resolved by declaring an override in class FilePersister.)
class FilePersister[T] extends Persister[T] with FileDatabase[T] with History with Mystery
^
Error:(48, 7) class MemoryPersister inherits conflicting members:
    method add in trait History of type ()Unit and
    method add in trait Mystery of type ()Unit
(Note: this can be resolved by declaring an override in class MemoryPersister.)class MemoryPersister[T] extends Persister[T] with MemoryDatabase[T] with History with Mystery
                               ^
                               
幸运的是这个错误消息已经包含了一些如何修复问题的信息。
这跟我们一开始使用特质时遇到的完全是相同的问题，我们可以使用如下的方式修复：
class FilePersister[T] extends Persister[T] with FileDatabase[T] with History with Mystery{
  override def add():Unit = super[History].add()
}
class MemoryPersister[T] extends Persister[T] with MemoryDatabase[T] with History with Mystery {
  override def add(): Unit = super[Mystery].add()
}

```