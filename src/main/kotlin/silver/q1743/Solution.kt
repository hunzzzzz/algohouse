package silver.q1743

import java.io.StreamTokenizer
import java.util.*

var aisle = arrayOf(intArrayOf()) // 화면
var visited = arrayOf(booleanArrayOf()) // 방문 여부
val fourDirection = listOf(listOf(0, 1), listOf(1, 0), listOf(-1, 0), listOf(0, -1)) // 4방향 탐색을 위한 리스트
const val TRASH = 1 // 음식물(1)을 표현한 문자

fun bfs(startX: Int, startY: Int): Int {
    val deque = ArrayDeque<Pair<Int, Int>>() // 탐색해야 할 위치를 저장하는 자료구조
    var biggest = 1 // 가장 큰 음식물(1)의 크기를 담을 변수 (시작 좌표도 크기에 포함되어야 하므로 초기값을 1로 설정)

    deque.add(Pair(startX, startY)) // 시작점에서 탐색을 시작
    visited[startX][startY] = true // 시작점에 대한 방문 여부 체크

    while (deque.isNotEmpty()) {
        val (x, y) = deque.poll() // 탐색할 기준 좌표

        for ((dx, dy) in fourDirection) {
            val nx = x + dx // 비교할 x좌표
            val ny = y + dy // 비교할 y좌표

            if (
                (nx in aisle.indices && ny in aisle[nx].indices) // 좌표 유효성 검사
                && !visited[nx][ny] // 방문 여부 체크
                && (aisle[nx][ny] == TRASH) // 음식물(1) 여부 체크
            ) {
                deque.add(Pair(nx, ny)) // 다음 탐색 대상으로 추가
                visited[nx][ny] = true // 방문 체크
                biggest++ // 음식물을 만나면 biggest+
            }
        }
    }

    return biggest
}

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }

        val row = int() // 행
        val column = int() // 열
        val numOfTrashes = int() // 음식물의 개수

        aisle = Array(row) { IntArray(column) { 0 } }
        visited = Array(row) { BooleanArray(column) { false } }

        // 데이터 저장
        repeat(numOfTrashes) { aisle[int() - 1][int() - 1] = 1 }

        var max = Int.MIN_VALUE // 음식물 크기의 최대값을 담을 변수

        for (i in aisle.indices) {
            for (j in aisle[i].indices) {
                // 방문하지 않은 음식물(1)만 탐색한다.
                if (aisle[i][j] == TRASH && !visited[i][j]) {
                    // 음식물 크기의 최대값 비교
                    bfs(startX = i, startY = j).let { if (it > max) max = it }
                }
            }
        }

        // 결과값 출력
        buildString { append("$max\n") }.let {
            with(System.out.bufferedWriter()) {
                write(it)
                flush()
                close()
            }
        }
    }
}