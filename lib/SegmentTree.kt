package lib

class SegmentTree(private val n: Int,
                  private val change: (Int, Int) -> Int,
                  private val result: (Int, Int) -> Int) {
  private val tr = IntArray(n * 4 + 5)

  private fun modifySingleNode(s: Int, t: Int, l: Int, r: Int, p: Int, k: Int) {
    if (s <= l && r <= t) {
      tr[p] = change(tr[p], k)
      return
    }
    val m = (l + r) / 2
    if (s <= m) {
      modifySingleNode(s, t, l, m, 2 * p, k)
    }
    if (m < t) {
      modifySingleNode(s, t, m + 1, r, 2 * p + 1, k)
    }
    tr[p] = result(tr[2 * p], tr[2 * p + 1])
  }

  private fun getResult(s: Int, t: Int, l: Int, r: Int, p: Int): Int {
    if (s <= l && r <= t) {
      return tr[p]
    }
    val m = (l + r) / 2
    return if (m in s..<t) {
      result(getResult(s, t, l, m, 2 * p), getResult(s, t, m + 1, r, 2 * p + 1))
    } else if (s <= m) {
      getResult(s, t, l, m, 2 * p)
    } else {
      getResult(s, t, m + 1, r, 2 * p + 1)
    }
  }

  fun modify(x: Int, y: Int) {
    modifySingleNode(x, x, 1, n, 1, y)
  }

  fun query(l: Int, r: Int): Int {
    return getResult(l, r, 1, n, 1)
  }
}