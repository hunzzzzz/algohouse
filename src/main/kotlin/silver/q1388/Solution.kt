package silver.q1388

import java.util.*

fun main() {
    val (row, column) = readln().split(' ').map { it.toInt() }

    val room = Array(row) { "" } // 방의 바닥
    repeat(row) { room[it] = readln() }

    val visited = Array(row) { BooleanArray(column) { false } } // 방문 여부
    var count = 0 // 연속되는 바닥 모양 개수

    for (i in 0..<row) {
        for (j in 0..<column) {
            if (!visited[i][j]) {
                val deque = ArrayDeque<Pair<Int, Int>>() // 탐색할 위치를 저장 (BFS 탐색)
                deque.offer(Pair(i, j)) // 시작점
                visited[i][j] = true

                while (deque.isNotEmpty()) {
                    val (x, y) = deque.poll() // 기준 좌표
                    // 2방향(가로 혹은 세로) 탐색을 위한 리스트
                    val twoDirectionList =
                        if (room[i][j] == '-') listOf(Pair(0, 1), Pair(0, -1)) else listOf(Pair(1, 0), Pair(-1, 0))
                    var loopCount = 0 // 반복문 횟수를 측정

                    // 2방향 탐색 (diff: x와 y의 변화량)
                    for (diff in twoDirectionList) {
                        val nx = x + diff.first // 탐색하고자 하는 x좌표
                        val ny = y + diff.second // 탐색하고자 하는 y좌표
                        if (
                            (nx in 0..<row && ny + diff.second in 0..column) // 탐색하고자 하는 좌표의 유효성 여부 확인
                            && (room[nx][ny] == room[i][j]) // 모양 여부 확인
                            && !visited[nx][ny] // 방문 여부 확인
                        ) {
                            visited[nx][ny] = true // 방문 체크
                            deque.add(Pair(nx, ny)) // 탐색할 위치 추가
                        } else loopCount++ // 조건문에 충족되지 않으면 loopCount 1 증가
                    }

                    // loopCount가 2인 경우 (= 조건문을 한 번도 충족되지 않은 경우) (= 주변에 일치하는 판자가 없는 경우)에 탐색을 종료하고 count++
                    if (loopCount == twoDirectionList.size) count++
                }
            }
        }
    }

    println(count)
}