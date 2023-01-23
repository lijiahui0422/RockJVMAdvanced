package lectures

import javax.swing.plaf.multi.MultiListUI
import scala.annotation.tailrec

object Generics {
  /*
   - what generics are
   - how to use generics
   - why they exist
  */

  // static type system : before runtime, at compile time, scala knows the type of every single expression
  // make assumptions assumptions about the data

  // Why generics?
  // 1. we can make assumptions about the types we work with
  // 2. we can reuse logic on potentially unrelated types.



  // define a list with Int type
//  trait MyList {
//    def head: Int
//    def tail: MyListAny
//  }
//
//  object EmptyAny extends MyListAny {
//    override def head = throw new NoSuchElementException()
//    override def tail = throw new NoSuchElementException()
//  }
//  case class NonEmpty(h: Int, t: MyList) extends MyList {
//    override def head: Int = h
//    override def tail: MyList = t
//  }
//
//  val myList: NonEmpty = NonEmpty(1, NonEmpty(2, NonEmpty(3, NonEmpty(4, Empty))))



  def main(args: Array[String]): Unit = {

    // A list implementation that extend to all TYPES
    trait MyList[A] {
      def head: A
      def tail: MyList[A]
    }

    case class Empty[A]() extends MyList[A] {
      override def head = throw new NoSuchElementException()
      override def tail = throw new NoSuchElementException()
    }
    case class NonEmpty[A](h: A, t: MyList[A]) extends MyList[A] {
      override def head: A = h
      override def tail: MyList[A] = t
    }

    val goodNumbers: MyList[Int] = NonEmpty(1, NonEmpty(2, NonEmpty(3, Empty())))

    println(goodNumbers.head)

    @tailrec
    def lastElement[A](list: MyList[A]): A = {
      if (list == Empty[A]()) throw new NoSuchElementException()
      else if (list.tail == Empty[A]()) list.head
      else lastElement(list.tail)
    }

    println(lastElement(goodNumbers))

  }

}
