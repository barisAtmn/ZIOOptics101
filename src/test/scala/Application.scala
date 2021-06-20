import zio._

// https://www.youtube.com/watch?v=-km5ohYhJa4&ab_channel=ZivergeZiverge
object Application extends App{
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = ???

  case class Children(name: Set[String] = Set.empty[String])
  case class Person(name:String, age: Int, children: Children)

  /**
   *  Whole = S
   *  Piece = A
   **/
  case class Lens[Whole, Piece](get: Whole => Piece, set: Piece => Whole => Whole)
  {
    self =>
    def update(whole:Whole)(f: Piece => Piece):Whole = {
      val old = get(whole)
      set(f(old))(whole)
    }

    def >>>[SubPiece](that: Lens[Piece, SubPiece]):SubPiece = {
      Lens(
        whole => that.get(self.get(whole)),
        subpiece => whole => self.set(that.set(subpiece)(self.get(whole)))(whole)
      )
    }
  }

  val person = Lens[Person, Children](person => person.children, // get
    children => person => person.copy(children = children) // set
  )


}

