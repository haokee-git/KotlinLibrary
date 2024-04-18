import lib.*
import java.util.*

val cin = Scanner(System.`in`)

fun main() {
  val s = cin.next()
  val t = cin.next()
  val p = StringAlgo.kmp(s, t)
  for (i in p) {
    print("$i ")
  }
}