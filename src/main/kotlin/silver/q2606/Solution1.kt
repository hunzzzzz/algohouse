package silver.q2606

import java.io.StreamTokenizer

var graph = arrayOf<ArrayList<Int>>()
var visited = booleanArrayOf()

/*
    graph = [[], [2, 5], [1, 3, 5], [2], [7], [1, 2, 6], [5], [4]]

    dfs(1) (visited[1] = true)
        -> dfs(2) (visited[2] = true)
            -> dfs(1) continue
            -> dfs(3) (visited[3] = true)
                -> dfs(2) continue
            -> dfs(5) (visited[5] = true)
                -> dfs(1) continue
                -> dfs(2) continue
                -> dfs(6) (visited[6] = true)
                    -> dfs(5) continue
        -> dfs(5) continue
 */

// 특정 노드를 방문하는 재귀 함수
fun dfs(node: Int) {
    // 방문한 노드를 true로 변경한다.
    visited[node] = true

    for (next in graph[node]) {
        // 이미 방문한 노드는 방문하지 않는다.
        if (visited[next]) continue
        dfs(node = next)
    }
}

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }

        val numOfNode = int() // 노드의 수
        val numOfLink = int() // 링크의 수
        visited = BooleanArray(numOfNode + 1) { false } // 방문 여부를 체크하는 Boolean 배열
        graph = Array(numOfNode + 1) { arrayListOf() } // 그래프 (인덱스 = 노드 번호, 값 = 연결된 노드들의 리스트)

        // 데이터 삽입
        repeat(numOfLink) {
            val first = int()
            val second = int()
            graph[first].add(second)
            graph[second].add(first)
        }

        // 재귀 시작
        dfs(node = 1)

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