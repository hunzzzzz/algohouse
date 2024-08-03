package silver.q21736

import java.io.StreamTokenizer
import java.util.*

var campus = arrayOf<String>() // 캠퍼스 지도
var visited = arrayOf(booleanArrayOf()) // 방문 여부
var locationOfDoyeon = Pair(0, 0) // 도연의 시작 위치
val fourDirection = listOf(listOf(0, 1), listOf(1, 0), listOf(-1, 0), listOf(0, -1)) // 4방향 탐색을 위한 리스트
const val DOYEON = 'I' // 도연이를 표시하는 문자 (I)
const val WALL = 'X' // 벽을 표시하는 문자 (X)
const val STUDENTS = 'P' // 사람을 표시하는 문자 (P)

fun bfs(): Int {
    val deque = ArrayDeque<Pair<Int, Int>>() // 탐색해야 할 위치를 저장하는 자료구조
    var count = 0 // 만날 수 있는 사람의 수를 담을 변수

    deque.add(locationOfDoyeon) // 도연의 시작 위치에서 탐색을 시작한다.

    while (deque.isNotEmpty()) {
        val (x, y) = deque.poll() // 탐색할 기준 좌표

        // 4방향 탐색 시작
        for ((dx, dy) in fourDirection) {
            val nx = x + dx // 비교할 x좌표
            val ny = y + dy // 비교할 y좌표

            if (
                (nx in campus.indices && ny in campus[nx].indices) // 좌표 유효성 검사
                && !visited[nx][ny] // 방문 이력 체크
                && campus[nx][ny] != WALL // 벽(X) 여부 체크
            ) {
                if (campus[nx][ny] == STUDENTS) count++ // 사람을 만나면 count++
                visited[nx][ny] = true // 방문 체크
                deque.add(Pair(nx, ny)) // 다음 탐색 대상으로 추가
            }
        }

    }

    return count
}

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }
        fun string() = nextToken().run { sval }

        val row = int() // 행
        val column = int() // 열

        campus = Array(row) { "" }
        visited = Array(row) { BooleanArray(column) { false } }

        // 데이터 저장
        repeat(row) { index ->
            val input = string()
            campus[index] = input

            if (input.contains(DOYEON))
                locationOfDoyeon = Pair(index, input.indexOf(DOYEON)) // 도연의 시작 좌표를 저장
        }

        // 결과값 출력
        buildString { append("${bfs().let { if (it == 0) "TT" else it }}\n") }.let {
            with(System.out.bufferedWriter()) {
                write(it)
                flush()
                close()
            }
        }
    }
}