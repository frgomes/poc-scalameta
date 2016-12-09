import scala.meta._

//see: https://www.youtube.com/watch?v=QM1iUe6IofM from 40:00s to 43:25s
class use(arg: Symbol*) extends scala.annotation.StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    val arg = this match {
      case q"new $_(..$names)" => names
      case _ => ???
    }
    println(s"****** TODO: use has parameters: ${arg}")
    defn.asInstanceOf[Stat]
  }
}
