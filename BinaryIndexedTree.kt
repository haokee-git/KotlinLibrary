import java.util.*

class BinaryIndexedTree<Ty>(private val size: Int,
                            private val default: () -> Ty,
                            private val function: (Ty, Ty) -> Ty) {
  private val tr = ArrayList<Ty>(size + 1)

  init {
    for (i in 0..size) {
      tr.add(default())
    }
  }

  private fun getLowBit(x: Int): Int {
    return x and -x
  }

  fun modify(x: Int, y: Ty) {
    var i = x
    while (i < size) {
      tr[i] = function(tr[i], y)
      i += getLowBit(i)
    }
  }

  fun query(x: Int): Ty {
    var i = x
    var res = default()
    while (i != 0) {
      res = function(res, tr[i])
      i -= getLowBit(i)
    }
    return res
  }
}