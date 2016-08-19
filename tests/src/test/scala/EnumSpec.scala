import utest._

@enum object Status {
  // object Starting
  object Running
}

object EnumSpec extends TestSuite {
   val tests = this {
     "Enumeration expansion"-{
       assert(Status.Running.id   == 0)
       assert(Status.Running.name == "fake")
     }
   }
}
