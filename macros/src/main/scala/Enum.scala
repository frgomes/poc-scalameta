import scala.annotation.compileTimeOnly
import scala.meta._


@compileTimeOnly("@enum not expanded")
class enum extends scala.annotation.StaticAnnotation {
  inline def apply(defn: Any) = meta {
    //XXX val q"""object $holder { ..$stats }""" = defn
    //XXX val enum =
    //XXX   q"""
    //XXX     object $holder {
    //XXX       sealed abstract class enum(val id: Int, val name: String) extends Ordered[enum] {
    //XXX         def compare(that: enum) = this.id - that.id
    //XXX       }
    //XXX       $expr(...$stats) extends enum(0, "fake")
    //XXX     }
    //XXX   """
    //XXX println(enum)
    //XXX enum

    val q"""object $holder { ..$mods object $entry extends $template }""" = defn
    val enum =
      q"""
        object $holder {
          sealed abstract class enum(val id: Int, val name: String) extends Ordered[enum] {
            def compare(that: enum) = this.id - that.id
          }
          case object $entry extends enum(0, "fake")
        }
      """
    enum
  }
}

/*
    println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv")
    println(holder)
    println(mods)
    println(entry)
    println(template)
    println(enum)
    println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^")

 */