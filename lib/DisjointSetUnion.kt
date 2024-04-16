package lib

import java.util.*

class DisjointSetUnion(private val size: Int) {
  private val father = IntArray(size + 1)

  init {
    Arrays.fill(father, -1)
  }

  fun leader(son: Int): Int {
    assert(son in 1..size)
    if (father[son] == -1) {
      return son
    }
    father[son] = leader(father[son])
    return father[son]
  }

  fun unite(u: Int, v: Int) {
    assert(u in 1..size && v in 1..size)
    val fu = leader(u)
    val fv = leader(v)
    if (fu != fv) {
      father[fu] = fv
    }
  }

  fun groups(): Set<Int> {
    val s = HashSet<Int>()
    for (i in 1..size) {
      s.add(leader(i))
    }
    return s
  }
}