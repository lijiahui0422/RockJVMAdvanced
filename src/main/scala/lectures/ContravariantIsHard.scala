package lectures

object ContravariantIsHard {
  def main(args: Array[String]): Unit = {
    val myList: List[Int] = List(1, 2, 3, 4)

    class Animal
    class Dog(name: String) extends Animal
    //question: if Dog<: Animal, does List[Dog] <:List[Animal] as well?
    // 1. If yes, then the type is called Covariant

    val lassie = new Dog("Lassie")
    val hachi = new Dog("Hachi")
    val laika = new Dog("Laika")

    val anAnimal: Animal = lassie // dog
    val myDogs: List[Animal] = List(lassie, hachi, laika) // list of dogs

    // 2. if no, then the type is called invariant
    class MyInvariantList[T]
    // won't compile
//    val mDogs: MyInvariantList[Dog] = new MyInvariantList[Animal]

    // 3. quite the opposite -- contravariant
    class MyContravariantList[-T]
    val myDogs2: MyContravariantList[Dog] = new MyContravariantList[Animal]

  }
}
