package lib

import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class Graph(private val n: Int,
            m: Int = 0) {
  data class Edge(val from: Int, val to: Int, val weight: Int)

  data class ForwardEdge(val to: Int, val weight: Int)

  private val edges = ArrayList<Edge>(m)
  private val forwardEdges = Array(n + 1) { ArrayList<ForwardEdge>() }

  fun addEdge(from: Int, to: Int, weight: Int) {
    assert(from in 1..n && to in 1..n)
    edges.add(Edge(from, to, weight))
    forwardEdges[from].add(ForwardEdge(to, weight))
  }

  operator fun get(from: Int) = forwardEdges[from]

  fun getEdges() = edges

  fun kruskal(cmp: Comparator<Edge> = compareBy { it.weight }): Int {
    val e = edges
    val st = DisjointSetUnion(n + 1)
    var ans = 0
    e.sortWith(cmp)
    for (i in e) {
      if (st.leader(i.from) != st.leader(i.to)) {
        st.unite(i.from, i.to)
        ans += i.weight
      }
    }
    return ans
  }

  fun floyd(inf: Int = 1e9.toInt()): Array<IntArray> {
    val dp = Array(n + 1) { IntArray(n + 1) { inf } }
    for (i in 1..n) {
      dp[i][i] = 0
    }
    for (e in edges) {
      dp[e.from][e.to] = e.weight
    }
    for (k in 1..n) {
      for (i in 1..n) {
        for (j in 1..n) {
          dp[i][j] = dp[i][j].coerceAtMost(dp[i][k] + dp[k][j])
        }
      }
    }
    return dp
  }

  fun dijkstra(s: Int, inf: Int): IntArray {
    val cmp: Comparator<ForwardEdge> = compareBy { it.weight }
    val q = PriorityQueue(cmp)
    val f = BooleanArray(n + 1)
    val d = IntArray(n + 1) { inf }
    q.add(ForwardEdge(s, 0))
    d[s] = 0
    while (q.isNotEmpty()) {
      val t = q.poll()
      if (f[t.to]) {
        continue
      }
      for (i in forwardEdges[t.to]) {
        if (d[t.to] + i.weight < d[i.to]) {
          d[i.to] = d[t.to] + i.weight
          q.add(ForwardEdge(i.to, d[i.to]))
        }
      }
    }
    return d
  }

  fun spfa(s: Int, inf: Int): IntArray {
    val q: Queue<Int> = LinkedList()
    val f = BooleanArray(n + 1)
    val d = IntArray(n + 1) { inf }
    q.add(s)
    d[s] = 0
    f[s] = true
    while (q.isNotEmpty()) {
      val t = q.poll()
      for (i in forwardEdges[t]) {
        if (d[t] + i.weight < d[i.to]) {
          d[i.to] = d[i.to] + i.weight
          if (!f[i.to]) {
            f[i.to] = true
            q.add(i.to)
          }
        }
      }
      f[t] = false
    }
    return d
  }
}