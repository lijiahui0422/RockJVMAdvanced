package lectures

object TypeClass {
  def main(args: Array[String]): Unit = {

    // define the implementation for certain types but not for others
    trait Summable[T] {
      def sumElements(list:List[T]): T
    }

    implicit object IntSummable extends Summable[Int] {
      override def sumElements(list: List[Int]): Int = list.sum
    }

    implicit object StrSummable extends Summable[String] {
      override def sumElements(list: List[String]): String = list.mkString("")
    }

    def processMyList[T](list:List[T])(implicit summable: Summable[T]): T = { // ad-hoc polymorphism
      // implement sum up , only works for int and string
      // for other types -- error
      // implicit
      summable.sumElements(list)
    }

    println(processMyList(List(1,2,3,4)))
    println(processMyList(List("great","time")))



  }

}
