import scala.meta._

//see: https://www.youtube.com/watch?v=QM1iUe6IofM from 40:00s to 43:25s
class use(arg: Symbol*) extends scala.annotation.StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    println(this.structure) // `this` is a scala.meta tree.
    val arg = this match {
      case q"new $_(${Term.Name(arg0: String)})"                                                         => Seq(arg0)
      case q"new $_(${Term.Name(arg0: String)}, ${Term.Name(arg1: String)})"                             => Seq(arg0, arg1)
      case q"new $_(${Term.Name(arg0: String)}, ${Term.Name(arg1: String)}, ${Term.Name(arg2: String)})" => Seq(arg0, arg1, arg2)
      case _ => ???
    }
    println(s"****** TODO: use has parameters: ${arg}")
    defn.asInstanceOf[Stat]
  }
}
