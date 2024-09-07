package chapter06

object Trait4 {
  def main(args: Array[String]): Unit = {
    val m = new MyBall()
    println(m.describe())
  }
}

// 菱形继承, Ball 被 ColorBall和CategoryBall同时继承
// ColorBall和CategoryBall 又被MyBall继承，构成了菱形继承
// 此时会出现特征叠加的问题

trait Ball {
  def describe(): String = "ball"
}

trait ColorBall extends Ball {
  val color: String = "red"
  override def describe(): String = color + super.describe()
}

trait CategoryBall extends Ball {
  val category: String = "foot"

  override def describe(): String = category + super.describe()
}

// 按照正常的叠加顺序，如果ColorBall和CategoryBall之间没有共同的父类，那么将从右往左，即先使用最右边的trait中的属性和方法
// 但是如果ColorBall和CategoryBall存在共同的父类，情况如下：
// 1. 混入的第一个特质是,CategoryBall -> Ball
// 2. 混入的第二个特质是,ColorBall -> Ball,发现了共同的特质，此时将会这两个拥有共同特质的特质叠加为: ColorBall -> CategoryBall -> Ball
// 3. 将子类MyBall放在临时叠加顺序的第一个，得到最终叠加顺序: MyBall -> ColorBall -> CategoryBall -> Ball
// 4. ColorBall再进行super.describe()调用时，调用的是CategoryBall的，这即是所谓的叠加
// 此时，如果想指定使用特定的父类的方法，可以直接指定，即super[CategoryBall].describe()
class MyBall extends CategoryBall with ColorBall {
  override def describe(): String = super.describe()
}

// 如果没有共同的父类Ball，那么最后的叠加顺序是：MyBall -> ColorBall -> CategoryBall，最左边的特质先插入叠加队列里，然后才是右边的
// 所以此时，如所使用super，则调用的是ColorBall

// 特质和抽象类
// 特质不可以带参数，而抽象类可以带参数
// 同时抽象类还是用来对事物的本质进行描述