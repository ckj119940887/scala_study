package DesignPatternInScala

import scala.collection.mutable

trait SimpleConnection {
  def getName():String
  def executeQuery(query:String):Unit
}

trait SimpleConnectionPrinter {
  def printSimpleConnection(connection:SimpleConnection):Unit
}

class SimpleMysqlConnection extends SimpleConnection{
  override def getName():String = "SimpleMySQLConnection"
  override def executeQuery(query:String):Unit = {
    System.out.println(s"Executing the query '$query' the MySQL way.")
  }
}

class SimplePgSqlConnection extends SimpleConnection {
  override def getName():String = "SimpePgSqlConnection"
  override def executeQuery(query:String):Unit = {
    System.out.println(s"Executing the query '$query' the PgSQL way.")
  }
}

class SimpleMysqlConnectionPrinter extends SimpleConnectionPrinter {
  override def printSimpleConnection(connection:SimpleConnection):Unit = {
    System.out.println(s"I require a MySQL connection. It is: '${connection.getName()}'")
  }
}

class SimplePgSqlConnectionPrinter extends SimpleConnectionPrinter{
  override def printSimpleConnection(connection:SimpleConnection):Unit = {
    System.out.println(s"I require a PgSQL connection. It is: '${connection.getName()}'")
  }
}

// the factory
abstract class BadDatabaseClient {
  def executeQuery(query:String):Unit = {
    val connection = connect()
    val connectionPrinter = getConnectionPrinter()
    connectionPrinter.printSimpleConnection(connection)
    connection.executeQuery(query)
  }

  // the factory method
  protected def connect():SimpleConnection
  protected def getConnectionPrinter():SimpleConnectionPrinter
}

class MysqlClient extends BadDatabaseClient {
  override protected def connect():SimpleConnection = new SimpleMysqlConnection

  override protected def getConnectionPrinter(): SimpleConnectionPrinter = new SimpleMysqlConnectionPrinter
}

class PgSqlClient extends BadDatabaseClient {
  override protected def connect():SimpleConnection = new SimplePgSqlConnection

  override protected def getConnectionPrinter(): SimpleConnectionPrinter = new SimpleMysqlConnectionPrinter
}

object Example extends App {
  val clientMySql:BadDatabaseClient = new MysqlClient
  val clientPgSql:BadDatabaseClient = new PgSqlClient

  clientMySql.executeQuery("SELECT * FROM users")
  clientPgSql.executeQuery("SELECT * FROM employees")
}

object Test extends App {

}