package silver.q6186

import java.util.*

var grass = arrayOf<String>() // 잔디밭
var visited = arrayOf(booleanArrayOf()) // 방문 여부를 체크

fun bfs(): Int {
    var count = 0 // 잔디 덩어리의 개수
    val deque = ArrayDeque<Pair<Int, Int>>() // 탐색해야 하는 좌표를 저장하는 자료구조

    for (i in grass.indices) {
        for (j in grass[i].indices) {
            // 잔디(#)가 아니거나, 이미 방문을 한 좌표는 탐색을 진행하지 않는다.
            if (grass[i][j] != '#' || visited[i][j]) continue

            // 시작 좌표 = (i, j)
            deque.add(Pair(i, j))

            while (deque.isNotEmpty()) {
                val (x, y) = deque.poll() // 기준 좌표

                // 방문 여부 체크
                visited[x][y] = true

                // 2방향 탐색 (diff: x와 y의 변화량)
                val twoDirectionList = listOf(Pair(0, 1), Pair(1, 0))
                var loopCount = 0 // 반복문 횟수를 측정

                for (diff in twoDirectionList) {
                    val nx = x + diff.first // 탐색하고자 하는 x좌표 (x + dx)
                    val ny = y + diff.second // 탐색하고자 하는 y좌표 (y + dy)
                    if (
                        (nx in grass.indices && ny in grass[x].indices) // 탐색하고자 하는 좌표의 유효성 여부 확인
                        && (grass[nx][ny] == '#') // 잔디(#) 여부 확인
                        && !visited[nx][ny] // 방문 여부 확인
                    ) {
                        visited[nx][ny] = true // 방문 체크
                        deque.add(Pair(nx, ny)) // 탐색할 위치 추가
                    } else loopCount++
                }

                // loopCount가 2인 경우 (= 조건문을 한 번도 충족되지 않은 경우) (= 주변에 잔디가 없는 경우)에 탐색을 종료하고 count++
                if (loopCount == twoDirectionList.size) count++
            }
        }
    }
    return count
}

fun main() {
    val (row, column) = readln().split(' ').map { it.toInt() }

    // 데이터 삽입
    grass = Array(row) { "" }
    visited = Array(row) { BooleanArray(column) { false } }
    repeat(row) { grass[it] = readln() }

    // 결과값 출력
    println(bfs())
}