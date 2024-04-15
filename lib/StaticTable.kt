import kotlin.math.*

class StaticTable(private val n: Int,
                  private val a: IntArray,
                  private val function: (Int, Int) -> Int) {
  private val length = ceil(log2(n.toDouble())).toInt()
  private val lg = IntArray(n + 1)
  private val dp = Array(n + 1) { IntArray(length + 1) }

  init {
    for (i in 1..n) {
      dp[i][0] = a[i]
    }
    for (i in 2..n) {
      lg[i] = lg[i / 2] + 1
    }
    for (j in 1..length) {
      for (i in 1..n - (1 shl j) + 1) {
        dp[i][j] = function(dp[i][j - 1], dp[i + (1 shl (j - 1))][j - 1])
      }
    }
  }

  fun query(l: Int, r: Int): Int {
    assert(l in 1..r && r <= n)
    val k = lg[r - l + 1]
    return max(dp[l][k], dp[r - (1 shl k) + 1][k])
  }
}