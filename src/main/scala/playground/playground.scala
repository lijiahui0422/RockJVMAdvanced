package playground

object playground {

  def main(args: Array[String]): Unit = {
    class Box[A](var content: A)

    abstract class Animal {
      def name: String
    }
    case class Cat(name: String) extends Animal
    case class Dog(name: String) extends Animal


    val myAnimal = Cat("lv")

    // one way of instantiation
    val myCatBox: Box[Cat] = new Box[Cat](Cat("Felix"))

    val aCat = Cat("Felix")
    val aCatBox = new Box(aCat)

    println(myCatBox.content)
    println(aCatBox.content)

    val myAnimalBox = new Box[Cat](Cat("lv"))


    // this doesn't compile
//    val myAnimalBox: Box[Animal] = myCatBox


    class ImmutableBox[+A](val content: A)
    val catBox: ImmutableBox[Cat] = new ImmutableBox[Cat](Cat("Felix"))
    val animalBox: ImmutableBox[Animal] = catBox // now this compiles
//    println(animalBox.content = Cat("Haruki"))




//    import scala.collection.mutable.ListBuffer
//
//    val bufInt: ListBuffer[Int] = ListBuffer[Int](1,2,3)
//    val bufAny: ListBuffer[Any] = bufInt
//    bufAny(0) = "Hello"
//    val firstElem: Int = bufInt(0)

  }

}
