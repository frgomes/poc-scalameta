import utest._

object Something {
   val a = 5
   val z = "z"

   @use(a, `_b`, C)
   def unwanted_function_declaration: Unit = {
     //FIXME: I would like to simply open a block with a "{"
     println(a)
     println(z)
   }
 }
