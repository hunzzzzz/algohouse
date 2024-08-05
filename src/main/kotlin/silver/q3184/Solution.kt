package silver.q3184

import java.util.*

var ground = arrayOf<String>() // 마당
var visited = arrayOf(booleanArrayOf()) // 방문 여부
val fourDirection = listOf(listOf(0, 1), listOf(1, 0), listOf(-1, 0), listOf(0, -1)) // 4방향 탐색을 위한 리스트
const val FENCE = '#' // 울타리(#)를 표현한 문자
const val SHEEP = 'o' // 양(o)을 표현한 문자
const val WOLF = 'v' // 늑대(v)를 표현한 문자

fun bfs(startX: Int, startY: Int): HashMap<Char, Int> {
    val deque = ArrayDeque<Pair<Int, Int>>() // 탐색해야 할 위치를 저장하는 자료구조
    var numOfSheep = 0 // 양의 수를 담을 변수
    var numOfWolf = 0 // 늑대의 수를 담을 변수

    if (ground[startX][startY] == SHEEP) numOfSheep++ else numOfWolf++ // 시작점 역시 양/늑대 수에 포함해야 한다.

    deque.add(Pair(startX, startY)) // 시작점에서 탐색을 시작
    visited[startX][startY] = true // 시작점에 대한 방문 여부 체크

    while (deque.isNotEmpty()) {
        val (x, y) = deque.poll() // 탐색할 기준 좌표

        for ((dx, dy) in fourDirection) {
            val nx = x + dx // 비교할 x좌표
            val ny = y + dy // 비교할 y좌표

            if (
                (nx in ground.indices && ny in ground[nx].indices) // 좌표 유효성 검사
                && !visited[nx][ny] // 방문 여부 체크
                && ground[nx][ny] != FENCE // 울타리(#) 여부 체크
            ) {
                deque.add(Pair(nx, ny)) // 다음 탐색 대상으로 추가
                visited[nx][ny] = true // 방문 체크
                if (ground[nx][ny] == SHEEP) numOfSheep++ else if (ground[nx][ny] == WOLF) numOfWolf++ // 양과 늑대의 수를 계산
            }
        }
    }

    return hashMapOf(SHEEP to numOfSheep, WOLF to numOfWolf)
}

fun main() {
    val (row, column) = readln().split(' ').map { it.toInt() } // 행, 열

    ground = Array(row) { "" }
    visited = Array(row) { BooleanArray(column) }

    // 데이터 저장
    repeat(row) { i -> ground[i] = readln() }

    var numOfSheep = 0 // 양의 수를 담을 변수
    var numOfWolf = 0 // 늑대의 수를 담을 변수

    for (i in ground.indices) {
        for (j in ground[i].indices) {
            // 방문하지 않은 양(o)이나 늑대(v)만 탐색한다.
            if (!visited[i][j] && (ground[i][j] == WOLF || ground[i][j] == SHEEP)) {
                // 해당 영역 내 다수를 이루는 양/늑대가 승리한다.
                bfs(startX = i, startY = j).let {
                    if (it[SHEEP]!! > it[WOLF]!!) numOfSheep += it[SHEEP]!! else numOfWolf += it[WOLF]!!
                }
            }
        }
    }

    // 결과값 출력
    buildString { append("$numOfSheep $numOfWolf\n") }.let {
        with(System.out.bufferedWriter()) {
            write(it)
            flush()
            close()
        }
    }
}