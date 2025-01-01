package Other

/*
class Food
abstract class Animal {
  def eat(food: Food)
}

// 无法编译如下内容，Cow的eat方法并没有重写Animal类的eat方法
// Cow类是Grass，而Animal类是Food
class Grass extends Food
class Cow extends Animal {
  override def eat(food: Grass): Unit = {}
}
*/

class Food
abstract class Animal {
  // SuitableFood必须是Food的子类
  // 使用抽象类型，允许子类来决定到底应该是具体是Food的哪个子类
  type SuitableFood <: Food
  def eat(food: SuitableFood)
}

class Grass extends Food
class Cow extends Animal {
  override type SuitableFood = Grass
  override def eat(food: Grass): Unit = {}
}

object AbstractType extends App {

}
