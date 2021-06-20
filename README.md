# OPTICS

- Optics are a group of purely functional abstractions to manipulate (get, set, modify, …) 
  immutable objects.
  
- Whenever we need to traverse and modify a nested data structure or we need to represent the same
  data in different ways, optics help us to write our code in a more succinct way.
  
- Another lib is Monocle. Access and transform immutable data
  Monocle is a Scala library which offers a simple yet powerful API to access and transform immutable data.

- **Lens**, the most famous optic (S is the Product, and A is an element inside S)
''' 
get(s: S): A
set(a: A): S => S
'''
  
- **Optional**, Optional allows zooming into a data structure. (S is the Product, and A is an element inside S)
'''
getOption: S => Option[A]
set: A => S => S  
'''
  
- **Prism**, an optic that allows us to select only part of a data model. 
'''
getOption: S => Option[A]
reverseGet: A => S
'''
  
- **Iso**, useful when trying to represent the same data in different ways.

## ZIO Optics

- Definition
trait Optic[-GetWhole, -SetWholeBefore, -SetPiece, +GetError, +SetError, +GetPiece, +SetWholeAfter] {
  def getOptic(whole: GetWhole): Either[GetError, GetPiece]
  def setOptic(piece: SetPiece)(whole: SetWholeBefore): Either[SetError, SetWholeAfter]
}

- type Lens[S, A] = Optic[S, S, A, Nothing, Nothing, A, S]
'''
trait Lens[S, A] {
  def getOptic(s: S): Either[Nothing, A]
  def setOptic(a: A)(s: S): Either[Nothing, S]
}  
'''
A Lens is an optic that accesses a field of a product type, such as a tuple or case class.
{
  val first: Lens[(String, Int), String] =
     Optic.first
}

- type Prism[S, A] = Optic[S, Any, A, OpticFailure, Nothing, A, S]
'''
trait Prism[S, A] {
  def getOptic(s: S): Either[OpticFailure, A]
  def setOptic(a: Any)(s: S): Either[Nothing, S]
}  
'''
A Prism is an optic that accesses a case of a sum type, such as the Left or Right cases of an
Either or one of the subtypes of a sealed trait.
{
  val right: Prism[Either[String, Int], Int] =
     Prism.right
}


- type Traversal[S, A] = Optic[S, S, Chunk[A], OpticFailure, OpticFailure, Chunk[A], S]
'''
trait Traversal[S, A] {
  def getOptic(s: S): Either[OpticFailure, Chunk[A]]
  def setOptic(as: Chunk[A])(s: S): Either[OpticFailure, S]
}
'''
A Traversal is an optic that accesses zero or more values in a collection, such as a Chunk.
