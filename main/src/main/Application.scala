package esgi.com
package main

object Application extends App {

  val environment = InputParser().parse("./input.txt")
  print(environment)
  println("It works")
}
