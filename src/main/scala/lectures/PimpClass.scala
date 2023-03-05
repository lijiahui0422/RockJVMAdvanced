package lectures

import scala.annotation.tailrec

object PimpClass extends App {

  implicit class RichInt( value:Int) {
    def isEven: Boolean = value % 2 == 0
    def sqrt: Double = math.sqrt(value)
    def times(function:() => Unit): Unit = {
      @tailrec
      def timesHelp(n: Int): Unit =
        if (n <= 0 ) ()
        else {
          function()
          timesHelp(n-1)
        }
      timesHelp(value)
    }
    def *[T](list:List[T]): List[T] = {
      def concat(n: Int): List[T] = {
        if (n <= 0) List()
        else concat(n-1) ++ list
      }
      concat(value)
    }
  }
  100.isEven





 // exercise
  implicit class RichString(str: String) {
    def asInt: Int = Integer.valueOf(str)
  }
  // implicit method
  implicit def stringToInt(str: String): Int = Integer.valueOf(str)
  println("6" / 2)

  println("123".asInt)
  println("abcef".map(c => (c + 1).asInstanceOf[Char]))
  3.times(() => println("scala rocks!"))
  println(3 * List(5,6))


 }
