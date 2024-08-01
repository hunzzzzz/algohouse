package silver.q2606

import java.io.StreamTokenizer
import java.util.*

var graph = arrayOf<ArrayList<Int>>()
var visited = booleanArrayOf()

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }

        val numOfNode = int() // 노드의 수
        val numOfLink = int() // 링크의 수
        val deque = ArrayDeque<Int>()

        visited = BooleanArray(numOfNode + 1) { false } // 방문 여부를 체크하는 Boolean 배열
        graph = Array(numOfNode + 1) { arrayListOf() } // 그래프 (인덱스 = 노드 번호, 값 = 연결된 노드들의 리스트)

        // 데이터 삽입
        repeat(numOfLink) {
            val first = int()
            val second = int()
            graph[first].add(second)
            graph[second].add(first)
        }

        /*
            graph = [[], [2, 5], [1, 3, 5], [2], [7], [1, 2, 6], [5], [4]]

            deque = [1] (visited[1] = true)
                deque = [2] (visited[2] = true)
                    1 -> continue
                    deque = [3] (visited[3] = true)
                        2 -> continue
                    deque = [5] (visited[5] = true)
                        1 -> continue
                        2 -> continue
                        deque = [6] (visited[6] = true)
                            5 -> continue
                5 -> continue
         */
        deque.add(1)

        while (deque.isNotEmpty()) {
            val node = deque.poll()
            // 방문한 노드를 true로 변경한다.
            visited[node] = true

            for (next in graph[node]) {
                // 이미 방문한 노드는 방문하지 않는다.
                if (visited[next]) continue
                deque.add(next)
            }
        }

        // visited에서 본인을 제외한 방문 노드 수를 계산한다.
        buildString { append("${visited.count { it } - 1}\n") }
            .let {
                with(System.out.bufferedWriter()) {
                    write(it)
                    flush()
                    close()
                }
            }
    }
}