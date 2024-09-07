package chapter06

object TypeRelated {
  def main(args: Array[String]): Unit = {
    val student:Son = new Son("ckj", 13)
    student.sayHi()
    student.Study()
    val person:Father = new Son("bob", 12)

    // 类型判断
    println(student.isInstanceOf[Son])
    println(student.isInstanceOf[Father])
    println(person.isInstanceOf[Son])

    // 类型转换
    if(person.isInstanceOf[Son]) {
      val newPerson = person.asInstanceOf[Son]
      newPerson.sayHi()
    }

    println(classOf[Son])

  }
}

class Father(val name: String, val age: Int) {
  def sayHi() = {
    println("hi from father " + name)
  }
}

class Son(name: String, age: Int) extends Father(name, age){
  override def sayHi() = {
    println("hi from son " + name)
  }

  def Study(): Unit = {
    println("student study")
  }
}
