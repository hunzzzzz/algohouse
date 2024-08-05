package silver.q13565

import java.util.*

var fiber = arrayOf<String>() // 섬유 물질
var visited = arrayOf(booleanArrayOf()) // 방문 여부
val fourDirection = listOf(listOf(0, 1), listOf(1, 0), listOf(-1, 0), listOf(0, -1)) // 4방향 탐색을 위한 리스트
const val CONDUCTOR = '0' // 도체를 표시하는 문자 (1)

fun bfs(startX: Int, startY: Int): Boolean {
    val deque = ArrayDeque<Pair<Int, Int>>() // 탐색해야 할 위치를 저장하는 자료구조

    deque.add(Pair(startX, startY)) // 시작점에서 탐색을 시작
    visited[startX][startY] = true // 시작점에 대한 방문 여부 체크

    while (deque.isNotEmpty()) {
        val (x, y) = deque.poll() // 탐색할 기준 좌표

        for ((dx, dy) in fourDirection) {
            val nx = x + dx // 비교할 x좌표
            val ny = y + dy // 비교할 y좌표

            if (
                (nx in fiber.indices && ny in fiber[nx].indices) // 좌표 유효성 검사
                && !visited[nx][ny] // 방문 여부 체크
                && (fiber[nx][ny] == CONDUCTOR) // 도체(0) 여부 체크
            ) {
                // 마지막 줄에 도착한 경우 true를 리턴한다.
                if (nx == fiber.size - 1) return true

                deque.add(Pair(nx, ny)) // 다음 탐색 대상으로 추가
                visited[nx][ny] = true // 방문 체크
            }
        }
    }

    return false
}

fun main() {
    val (row, column) = readln().split(' ').map { it.toInt() } // 행, 열

    fiber = Array(row) { "" }
    visited = Array(row) { BooleanArray(column) { false } }

    // 데이터 저장
    repeat(row) { i -> fiber[i] = readln() }

    // 시작점은 반드시 첫 줄(fiber[0])에 위치한다.
    for (y in fiber[0].indices) {
        // 방문하지 않은 첫 줄의 도체(0)만 탐색한다.
        if (fiber[0][y] == CONDUCTOR && !visited[0][y]) {
            if (bfs(startX = 0, startY = y)) {
                println("YES")
                return
            }
        }
    }

    println("NO")
}