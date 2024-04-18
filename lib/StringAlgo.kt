package lib

class StringAlgo {
  companion object {
    @JvmStatic
    fun getHash(s: String, b: UInt = 127U): ULong {
      var res = 0UL
      for (c in s) {
        res = res * b + c.code.toUInt()
      }
      return res
    }

    @JvmStatic
    fun kmp(s1: String, s2: String): ArrayList<Int> {
      val s = "$s1 "
      val t = "$s2 "
      val nxt = IntArray(t.length + 1)
      val pos = ArrayList<Int>()
      var j = 0
      nxt[1] = 0
      for (i in 1..<t.length) {
        while (j != 0 && t[i] != t[j]) {
          j = nxt[j]
        }
        nxt[i + 1] = if (t[i] == t[j]) ++j else 0
      }
      j = 0
      for (i in s.indices) {
        while (j != 0 && s[i] != t[j]) {
          j = nxt[j]
        }
        j += if (s[i] == t[j]) 1 else 0
        if (j == t.length) {
          pos.add(i - t.length + 2)
        }
      }
      return pos
    }
  }
}