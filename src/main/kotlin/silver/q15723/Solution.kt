package silver.q15723

import java.util.*

// 노드와 링크를 표현한 그래프 (key: 출발 노드, value: 링크로 연결된 목표 노드들)
var graph = hashMapOf<String, MutableList<String>>()

fun dfs(from: String, to: String): Boolean {
    val deque = ArrayDeque<String>() // 탐색해야 할 위치를 저장하는 자료구조
    deque.add(from) // 시작 노드를 deque에 추가

    while (deque.isNotEmpty()) {
        val node = deque.poll() // 탐색을 시작할 노드

        // null인 경우, 더 이상 갈 수 있는 경로가 없다는 뜻이다.
        if (graph[node] != null) {
            for (x in graph[node]!!) {
                if (x == to) return true // 목표 노드에 도착하면 true를 리턴한다.
                else deque.add(x) // 다음 탐색 대상으로 노드를 추가
            }
        }
    }

    return false
}

fun main() {
    val numOfPremises = readln().toInt() // 전제의 개수

    // 전제
    repeat(numOfPremises) {
        readln().split("is").map { it.trim() }.let {
            val from = it[0]
            val to = it[1]

            graph.getOrPut(from) { mutableListOf() }.add(to) // 데이터 저장
        }
    }

    val numOfResult = readln().toInt() // 결과의 개수

    // 결론의 참/거짓 여부 출력
    buildString {
        repeat(numOfResult) {
            readln().split("is").map { it.trim() }.let {
                val from = it[0]
                val to = it[1]

                // bfs 탐색의 결과에 따른 결과값 출력
                dfs(from = from, to = to).let { result ->
                    if (result) append("T\n") else append("F\n")
                }
            }
        }
    }.let {
        with(System.out.bufferedWriter()) {
            write(it)
            flush()
            close()
        }
    }
}