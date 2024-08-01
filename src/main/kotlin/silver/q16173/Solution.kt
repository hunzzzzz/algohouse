package silver.q16173

import java.io.StreamTokenizer
import java.util.*

var numbers = arrayOf(intArrayOf()) // 게임 말판
var visited = arrayOf(booleanArrayOf()) // 방문 여부를 체크
const val SUCCESS = "HaruHaru"
const val FAILURE = "Hing"

fun bfs(): Boolean {
    val deque = ArrayDeque<Pair<Int, Int>>() // 탐색해야 하는 좌표를 저장하는 자료구조
    deque.add(Pair(0, 0)) // 시작 좌표 = (0,0)

    while (deque.isNotEmpty()) {
        val (x, y) = deque.poll() // 기준 좌표

        // 출구에 도착한 경우, 출구의 방문 여부(=게임의 성공 여부)를 리턴
        if (numbers[x][y] == -1) return visited[x][y]

        // 방문 여부 체크
        visited[x][y] = true

        // 2방향 탐색 (diff: x와 y의 변화량)
        val twoDirectionList = listOf(Pair(0, numbers[x][y]), Pair(numbers[x][y], 0))

        for (diff in twoDirectionList) {
            val nx = x + diff.first // 탐색하고자 하는 x좌표 (x + dx)
            val ny = y + diff.second // 탐색하고자 하는 y좌표 (y + dy)
            if (
                (nx in numbers.indices && ny in numbers.indices) // 탐색하고자 하는 좌표의 유효성 여부 확인
                && !visited[nx][ny] // 방문 여부 확인
            ) {
                visited[nx][ny] = true // 방문 체크
                deque.add(Pair(nx, ny)) // 탐색할 위치 추가
            }
        }
    }

    return false
}

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }

        val size = int()

        // 데이터 삽입
        numbers = Array(size) { IntArray(size) { 0 } }
        visited = Array(size) { BooleanArray(size) { false } }
        repeat(size) { i -> repeat(size) { j -> numbers[i][j] = int() } }

        // 결과값 출력
        buildString { append("${if (bfs()) SUCCESS else FAILURE}\n") }
            .let {
                with(System.out.bufferedWriter()) {
                    write(it)
                    flush()
                    close()
                }
            }
    }
}