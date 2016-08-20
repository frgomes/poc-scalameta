import scala.annotation.compileTimeOnly
import scala.meta._


@compileTimeOnly("@enum not expanded")
class enum extends scala.annotation.StaticAnnotation {
  inline def apply(defn: Any) = meta {
    val q"""object $holder { ..$stats }""" = defn
    //val q"""..$xs""" =
    val xs =
      stats.zipWithIndex.map {
        case (qq, idx) =>
          qq match {
            case q"""..$mods object $entry extends $template""" =>
              q"""case object $entry extends enum($idx)"""
            case tree: Tree => tree
          }
      }
    val enum =
      q"""
        object $holder {
          sealed abstract class enum(val ordinal: Int) extends Ordered[enum] {
            def compare(that: enum): Int = this.ordinal - that.ordinal
            val name: String = {
              val s = this.getClass.getSimpleName
              s.substring(0, s.length-1)
            }
          }
          ..$xs
        }
      """
    //--- println(enum)
    enum
  }
}
