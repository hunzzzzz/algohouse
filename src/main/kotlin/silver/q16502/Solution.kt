package silver.q16502

import java.io.StreamTokenizer

var numOfTest = 0 // 테스트 케이스의 수
var numOfLink = 0 // 링크(간선)의 수
var graph = hashMapOf<Char, MutableList<Pair<Char, Double>>>()
var percentage = hashMapOf<Char, Double>() // A~D에 대한 확률을 담은 배열

// now: 현재 노드, p: 확률, count: 탐색한 경로의 길이
fun dfs(now: Char, p: Double, count: Int) {
    // 종료 시간(numOfTest * 10분)이 지난 경우, dfs 탐색을 종료한다.
    if (count == numOfTest) {
        percentage[now] = percentage.getOrDefault(now, 0.0) + p * 100 // 모든 경우의 확률을 누적하여 더한다.
        return
    }

    // 다음 노드로 이동한다.
    for ((nextNode, nextP) in graph[now]!!) {
        dfs(now = nextNode, p = p * nextP, count = count + 1)
    }
}

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }
        fun double() = nextToken().run { nval }
        fun char() = nextToken().run { sval }.first()

        numOfTest = int()
        numOfLink = int()

        /*
            graph = { A=[(B, 1.0)], B=[(C, 0.3), (D, 0.7)], C=[(A, 0.6), (D, 0.4)], D=[(D, 1.0)] }

            1) dfs('A', 0.25, 0)
                -> dfs('B', 0.25 * 1.0, 1) = dfs('B', 0.25, 1)
                    -> dfs('C', 0.25 * 0.3, 2) = dfs('C', 0.075, 2) -> percentage['C'] = 7.5
                    -> dfs('D', 0.25 * 0.7, 2) = dfs('D', 0.175, 2) -> percentage['D'] = 17.5
            2) dfs('B', 0.25, 0)
                -> dfs('C', 0.25 * 0.3, 1) = dfs('C', 0.075, 1)
                    -> dfs('A', 0.075 * 0.6, 2) = dfs('A', 0.045, 2) -> percentage['A'] = 4.5
                    ...
         */

        // 데이터 삽입
        repeat(numOfLink) {
            val start = char() // 출발 노드
            val end = char() // 도착 노드
            val p = double() // 확률

            // 출발 노드에 대한 Pair(도착 노드, 확률)을 저장한다.
            if (graph.containsKey(start)) graph[start]!!.add(Pair(end, p))
            else graph[start] = mutableListOf(Pair(end, p))
        }

        // A부터 D까지 모든 경로를 탐색한다.
        for (ch in listOf('A', 'B', 'C', 'D')) {
            dfs(now = ch, p = 0.25, count = 0)
        }

        // 결과값을 출력한다.
        buildString {
            for (ch in listOf('A', 'B', 'C', 'D')) {
                append("%.3f\n".format(percentage[ch]))
            }
        }.let {
            with(System.out.bufferedWriter()) {
                write(it)
                flush()
                close()
            }
        }
    }
}