# 面向切面编程与组件
```
面向切面编程，即 AOP(Aspect-oriented programming)，解决了一个通用的功能，贯穿于整个应用，但是又无法使用传统的面向对象技术抽象成一个单独的模块。
这种重复的功能通常被引用为“横切关注点(cross-cutting concerns)”。
一个通用的例子是日志——通常日志记录器会在一个类中创建，然后在类的方法中调用这个记录器的方法。
这有助于调试和追踪应用中的事件，但由于应用实际的功能没什么相关性。
```

```
trait DataReader{
  def readData():List[Person]
  def readDataInefficiently():List[Person]
}

class DataReaderImpl extends DataReader{
  implicit val formats = DefaultFormats
  private def readUnitimed():List[Person] = 
    parse(StreamInput(getClass.getResourceAsStream("/users/json"))).
      extract[List[Person]]

  override def readData():List[Person] = readUntimed()

  override def readDataInefficiently():List[Person] = {
    (1 to 10000).foreach{
      case num => readUntimed()
    }
    readUntimed()
  }
}
如果你想优化你的代码并查看运行缓慢的原因呢？
上面的代码并没有提供这种能力，因此我们要做一些额外的工作来对应用计时并查看它是如何执行的。
```

## 不使用 AOP
```
有一种基本的方法来进行计时。
我们可以把println语句包裹起来，或者让计时称为DataReaderImpl类方法的一部分。
通常，将计时作为方法的一部分会是一个更好的选择，因为这个方法可能会在不同的地方被调用，同时它们的性能也取决于传入的参数和一些其他因素。
基于我们所说，这也就是DataReaderImpl类将会如何被重构以支持计时的方式：

class DataReaderImpl extends DataReader {
  implicit val formats = DefaultFormats
  private def readUnitimed():List[Person] = parse(StreamInput(getClass.getResourceAsStream("users.json"))).extract[List[Person]]
  override def readData(): List[Person] = {
    val startMillis = System.currentTimeMillis()
    val result = readUnitimed()
    val time = System.currentTimeMillis() - startMillis
    System.err.println(s"readData took $time milliseconds")
    result
  }

  override def readDataInefficiently():List[Person] = {
    val startMillis = System.currentTimeMillis()
    (1 to 1000) foreach {
      case num => readUntimed()
    }
    val result = readUntimed()
    val time = System.currentTimeMillis() - startMillis
    System.err.println(s"readDataInefficiently took ${time} milliseconds.")
    result
  }
}
```

## 使用 AOP
```
向我们的方法中添加计时代码将会引入重复代码同时也使代码难以追踪，尽管是一个很小的例子。
现在，假如我们同样需要打印一些日志或进行一些其他活动。AOP 将会帮助分离这些关注点。
我们可以把DataReadImpl重置到一开始的状态，这时它不再打印任何日志。
现在创建另一个名为LoggingDataReader的特质，它扩展自DataReader并拥有以下内容：
trait LoggingDataReader extends DataReader {
  abstract override def readData(): List[Person] = {
    val startMillis = System.currentTimeMillis()
    val result = super.readData()
    val time = System.currentTimeMillis() - startMillis
    System.err.pringln(s"readData took $time milliseconds.")
    result
  }

  abstract override def readDataInefficiently():List[Person] = {
    val startMillis = System.currentTimeMillis()
    val result = super.readDataInefficiently()
    val time = System.currentTimeMillis() - startMillis
    System.err.println(s"readDataIneffieciently took $time milliseconds.")
    result
  }
}

这里有趣的地方在于abstract override修饰符。它提醒编译器我们会进行叠加性(stackable)的修改。
如果我们不使用该修饰符，编译将会失败.

abstract override 使用规则：
1.abstract override 方法只能出现在特质中，不能直接用于类。
2.abstract override 方法要求一个具体的实现存在，必须调用父级方法（通过 super）。
3.如果没有父级实现，调用 super 会导致编译错误。
4.由于 abstract override 方法是抽象的，直接定义在特质中无法使用，只有在混入其他具体实现的类或特质时才能生效。

使用 AOP 的优势是很明显的——实现不会被其他代码污染。
再者，我们可以以同样的方式添加更多修改——更多的日志、重试逻辑、回滚等等。
所有这些都可以通过创建一个新特质并扩展DataReader接口，然后在创建具体实现的实例中混入即可。
当然，我们可以同时应用多个修改，它们将会按顺序执行，而顺序将会遵循线性化原则。
```