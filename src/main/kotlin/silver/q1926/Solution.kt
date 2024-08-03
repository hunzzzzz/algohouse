package silver.q1926

import java.io.StreamTokenizer
import java.util.*

var paper = arrayOf(intArrayOf()) // 캠퍼스 지도
var visited = arrayOf(booleanArrayOf()) // 방문 여부
val fourDirection = listOf(listOf(0, 1), listOf(1, 0), listOf(-1, 0), listOf(0, -1)) // 4방향 탐색을 위한 리스트
const val PAINTING = 1 // 그림을 표시하는 문자 (1)

fun bfs(startX: Int, startY: Int): Int {
    val deque = ArrayDeque<Pair<Int, Int>>() // 탐색해야 할 위치를 저장하는 자료구조
    var area = 0 // 그림 넓이를 계산할 변수

    deque.add(Pair(startX, startY)) // 시작점에서 탐색을 시작
    visited[startX][startY] = true // 시작점에 대한 방문 여부 체크

    while (deque.isNotEmpty()) {
        val (x, y) = deque.poll() // 탐색할 기준 좌표

        // 4방향 탐색
        for ((dx, dy) in fourDirection) {
            val nx = x + dx // 비교할 x좌표
            val ny = y + dy // 비교할 y좌표

            if (
                (nx in paper.indices && ny in paper[nx].indices) // 좌표 유효성 검사
                && !visited[nx][ny] // 방문 여부 체크
                && paper[nx][ny] == PAINTING // 그림(1) 여부 체크
            ) {
                deque.add(Pair(nx, ny)) // 다음 탐색 대상으로 추가
                visited[nx][ny] = true // 방문 체크
                area++ // 그림을 만나면 area++
            }
        }
    }

    return area + 1 // 시작점의 좌표도 넓이에 포함해야 한다.
}

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }

        val row = int() // 행
        val column = int() // 열

        paper = Array(row) { IntArray(column) }
        visited = Array(row) { BooleanArray(column) }

        // 데이터 저장
        repeat(row) { i -> repeat(column) { j -> paper[i][j] = int() } }

        var numOfPaintings = 0 // 그림의 개수
        var max = 0 // 그림 넓이의 최대값을 담을 변수

        for (i in paper.indices) {
            for (j in paper[i].indices) {
                // 방문하지 않은 그림(1)만 탐색한다.
                if (paper[i][j] == PAINTING && !visited[i][j]) {
                    bfs(startX = i, startY = j).let {
                        if (it > max) max = it
                    } // 넓이의 최대값 비교
                    numOfPaintings++
                }
            }
        }

        buildString { append("${numOfPaintings}\n${max}\n") }.let {
            with(System.out.bufferedWriter()) {
                write(it)
                flush()
                close()
            }
        }
    }
}