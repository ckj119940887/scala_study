package chapter06

object Trait5 {
  def main(args: Array[String]): Unit = {
    val user = new RegisterUser("ckj", "kkkk")
    user.insert()
  }
}

class User(val name: String, val password: String)

// 数据库
trait UserDao {
  // 特征注入，相当于特质中已经有一个User对象了，直接引用其属性
  _ : User =>

  // 向数据库插入数据
  def insert(): Unit = {
    println(s"insert into db: ${this.name}")
  }
}

class RegisterUser(name: String, password: String) extends User(name, password) with UserDao {

}