package lectures

import scala.None
import scala.annotation.tailrec

object AdvancedPatternMatching {
  def main(args: Array[String]): Unit = {

    val numbers = List(1)
    numbers match {
      case head :: Nil => println(s"the only element is $head")
      case _ =>
    }

    // in a case where you can not create a case class but still need some pattern matching functions on the class
    class Person(val name: String, val age: Int)

    // "unapply" is trying to take an object and return its arguments while apply is a constructor which takes arguments and
    // return an object.
    object PersonPattern {
      def unapply(person: Person): Option[(String,Int)] =
        if (person.age < 21) None
        else Some((person.name, person.age))

      def unapply(age: Int): Option[String] =
        Some(if (age< 21) "minor" else "major")
    }


    val bob = new Person("Bob", 25)
    val greeting = bob match {
      case PersonPattern(name, age) => s"Hi my name is $name and my age is $age"
    }
    println(greeting)

    val legalStatus = bob.age match {
      case PersonPattern(status) => println(s"my legal status is $status")
    }



    // exercise

    val n = 45
    val mathProperty = n match {
      case x if (x > -10 && x < 10) => "single digit"
      case x if (x % 2 == 0) => "even number"
      case _ => "no property"
    }
    println(mathProperty)

    //alternative way

    object even {
      def unapply(arg: Int): Boolean = arg % 2 == 0
    }

    object singleDigit {
      def unapply(arg: Int): Boolean = arg > -10 && arg < 10
    }

    val newMathProperty = n match {
      case singleDigit() => "single digit"
      case even() => "even number"
      case _ => "no property"
    }
    println(newMathProperty)

    // infix patterns
    case class Or[A, B](a:A, b:B) // such type is called "Either"
    val either = Or(2, "two")
    val humanDescription = either match {
//      case Or(number, text) => s"$number is written as $text"
      case number Or text => s"$number is written as $text"

    }
    println(humanDescription)

    //decomposing sequence --- don't  understand why this is needed-- only for customized collections?
    val newNumbers = List(1)
    val vararg = newNumbers match {
      case List(1, _*) => "starting with 1"
    }
    println(vararg)

    abstract class MyList[+A] {
      def head: A = ???
      def tail: MyList[A] = ???
    }

    case object Empty extends MyList[Nothing]
    case class Cons[+A](override val head: A, override val tail: MyList[A]) extends MyList[A]

    object MyList {
      def unapplySeq[A](list: MyList[A]): Option[Seq[A]] = {
        if (list == Empty) Some(Seq.empty)
        else unapplySeq(list.tail).map(list.head +: _)
      }
    }

    val myList: MyList[Int]= Cons(1, Cons(2, Cons(3, Empty)))


    // custom return types for unapply

  }

}
