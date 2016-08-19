import scala.annotation.compileTimeOnly
import scala.meta._


@compileTimeOnly("@enum not expanded")
class enum extends scala.annotation.StaticAnnotation {
  inline def apply(defn: Any) = meta {
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
    val q"""object $holder { ..$stats }""" = defn
    val enum =
      q"""
        object $holder {
          sealed abstract class enum(val id: Int, val name: String) extends Ordered[enum] {
            def compare(that: enum) = this.id - that.id
          }
          $expr(...$stats) extends enum(0, "fake")
        }
      """
    println(enum)
    enum


    println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv")
    println(holder)
    println(mods)
    println(entry)
    println(template)
    println(enum)
    println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^")

 */