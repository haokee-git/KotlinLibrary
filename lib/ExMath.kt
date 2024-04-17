package lib

class ExMath {
  companion object {
    @JvmStatic
    fun gcd(n: Long, m: Long): Long = if (m == 0L) n else gcd(m, n % m)

    @JvmStatic
    fun lcm(n: Long, m: Long) = n / gcd(n, m) * m

    @JvmStatic
    fun modPow(a: Long, b: Long, mod: Long): Long {
      assert(b >= 0)
      var base = a % mod
      var res = b
      var ans = 1L
      while (res != 0L) {
        if ((res and 1) == 1L) {
          ans = ans * base % mod
        }
        base = base * base % mod
        res /= 2
      }
      return ans
    }
  }
}