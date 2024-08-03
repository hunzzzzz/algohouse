package silver.q4963

import java.io.StreamTokenizer
import java.util.*

var ocean = arrayOf(intArrayOf()) // 바다 지도
var visited = arrayOf(booleanArrayOf()) // 방문 여부
val eightDirection = listOf(
    listOf(0, 1), listOf(1, 0), listOf(-1, 0), listOf(0, -1),
    listOf(1, 1), listOf(-1, -1), listOf(1, -1), listOf(-1, 1)
) // 8방향 탐색을 위한 리스트
const val LAND = 1 // 땅을 표시하는 숫자 (1)

fun bfs(startX: Int, startY: Int) {
    val deque = ArrayDeque<Pair<Int, Int>>() // 탐색해야 할 위치를 저장하는 자료구조

    deque.add(Pair(startX, startY)) // 시작점에서 탐색을 시작
    visited[startX][startY] = true // 시작점에 대한 방문 여부 체크

    while (deque.isNotEmpty()) {
        val (x, y) = deque.poll() // 탐색할 기준 좌표

        // 8방향 탐색 시작
        for ((dx, dy) in eightDirection) {
            val nx = x + dx // 비교할 x좌표
            val ny = y + dy // 비교할 y좌표

            if (
                (nx in visited.indices && ny in visited[nx].indices) // 좌표 유효성 검사
                && !visited[nx][ny] // 방문 여부 체크
                && ocean[nx][ny] == LAND // 섬(1) 여부 체크
            ) {
                visited[nx][ny] = true // 방문 체크
                deque.add(Pair(nx, ny)) // 다음 탐색 대상으로 추가
            }
        }
    }
}

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }

        var row: Int // 행
        var column: Int // 열

        buildString {
            while (true) {
                column = int()
                row = int()

                // 0, 0을 입력받으면 반복문을 종료한다.
                if (row == 0 && column == 0) break

                ocean = Array(row) { IntArray(column) { 0 } }
                visited = Array(row) { BooleanArray(column) { false } }

                // 데이터 저장
                repeat(row) { i -> repeat(column) { j -> ocean[i][j] = int() } }

                // 결과를 문자열에 저장
                var count = 0
                for (i in ocean.indices) {
                    for (j in ocean[i].indices) {
                        // 방문하지 않은 땅(1)만 탐색한다.
                        if (ocean[i][j] == LAND && !visited[i][j]) {
                            bfs(startX = i, startY = j)
                            count++
                        }
                    }
                }
                append("${count}\n")
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