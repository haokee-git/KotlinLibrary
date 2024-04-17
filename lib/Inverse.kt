package lib

class Inverse {
  companion object {
    private var x = 0L
    private var y = 0L

    private fun exGcd(a: Long, b: Long): Pair<Long, Long> {
      if (b == 0.toLong()) {
        x = 1L
        y = 0L
        return Pair(x, y)
      }
      val (x1, y1) = exGcd(b, a % b)
      x = y1
      y = x1 - y1 * (a / b)
      return Pair(x, y)
    }

    @JvmStatic
    fun get(a: Long, b: Long): Long {
      return exGcd(a, b).first
    }

    @JvmStatic
    fun getArray(n: Int, m: Long): LongArray {
      val inv = LongArray(n + 1)
      inv[1] = 1L
      for (i in 2..n) {
        inv[i] = (m - m / i) * inv[(m % i).toInt()] % m;
      }
      return inv
    }
  }
}