package silver.q21938

import java.io.StreamTokenizer
import java.util.*

var screen = arrayOf(intArrayOf()) // 화면
var visited = arrayOf(booleanArrayOf()) // 방문 여부
val fourDirection = listOf(listOf(0, 1), listOf(1, 0), listOf(-1, 0), listOf(0, -1)) // 4방향 탐색을 위한 리스트
var T = 0 // T의 값

fun bfs(startX: Int, startY: Int) {
    val deque = ArrayDeque<Pair<Int, Int>>() // 탐색해야 할 위치를 저장하는 자료구조

    deque.add(Pair(startX, startY)) // 시작점에서 탐색을 시작
    visited[startX][startY] = true // 시작점에 대한 방문 여부 체크

    while (deque.isNotEmpty()) {
        val (x, y) = deque.poll() // 탐색할 기준 좌표

        for ((dx, dy) in fourDirection) {
            val nx = x + dx // 비교할 x좌표
            val ny = y + dy // 비교할 y좌표

            if (
                (nx in screen.indices && ny in screen[nx].indices) // 좌표 유효성 검사
                && !visited[nx][ny] // 방문 여부 체크
                && (screen[nx][ny] >= T) // 물체(> T) 여부 체크
            ) {
                deque.add(Pair(nx, ny)) // 다음 탐색 대상으로 추가
                visited[nx][ny] = true // 방문 체크
            }
        }
    }
}

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }

        val row = int() // 행
        val column = int() // 열

        screen = Array(row) { IntArray(column) }
        visited = Array(row) { BooleanArray(column) }

        // 데이터 저장
        repeat(row) { i ->
            repeat(column) { j ->
                screen[i][j] = (int() + int() + int()) / 3
            }
        }

        T = int()

        var count = 0 // 물체의 개수를 담을 변수

        for (i in screen.indices) {
            for (j in screen[i].indices) {
                // 방문하지 않은 물체(>= T)만 탐색한다.
                if (screen[i][j] >= T && !visited[i][j]) {
                    bfs(startX = i, startY = j)
                    count++
                }
            }
        }

        // 결과값 출력
        buildString { append("${count}\n") }.let {
            with(System.out.bufferedWriter()) {
                write(it)
                flush()
                close()
            }
        }
    }
}