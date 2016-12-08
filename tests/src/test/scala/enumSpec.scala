import utest._

@enum
object Status {
  object Starting
  object Running
}

object EnumSpec extends TestSuite {
   val tests = this {
     "Enumerations"-{
       "ordinals"-{
         assert(Status.Starting.ordinal == 0)
         assert(Status.Running .ordinal == 1)
       }
       "names"-{
         assert(Status.Starting.name == "Starting")
         assert(Status.Running .name == "Running")
       }
     }
   }
}
