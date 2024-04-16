package lib

class Prime {
  companion object {
    fun isPrime(x: Long): Boolean {
      if (x <= 1L) {
        return false
      }
      var i = 2L
      while (i * i <= x) {
        if (x % i == 0L) {
          return false
        }
        i++
      }
      return true
    }

    fun getPrime(n: Int): ArrayList<Int> {
      val p = ArrayList<Int>()
      val f = Array(n + 1) { false }
      for (i in 2..n) {
        if (!f[i]) {
          p.add(i)
        }
        for (j in p) {
          if (i * j > n) {
            break
          }
          f[i * j] = true
          if (i % j == 0) {
            break
          }
        }
      }
      return p
    }
  }
}