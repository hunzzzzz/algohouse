package silver.q6186

import java.util.*

fun main() {
    val (row, column) = readln().split(' ').map { it.toInt() }

    val grass = Array(row) { "" } // 잔디밭
    repeat(row) { grass[it] = readln() }

    val visited = Array(row) { BooleanArray(column) { false } } // 방문 여부
    var count = 0 // 잔디 뭉치 개수

    for (i in 0..<row) {
        for (j in 0..<column) {
            // 잔디(#)이고, 방문을 아직 하지 않은 경우에만 탐색을 진행한다.
            if (grass[i][j] == '#' && !visited[i][j]) {
                val deque = ArrayDeque<Pair<Int, Int>>() // 탐색할 위치를 저장 (BFS 탐색)
                deque.offer(Pair(i, j)) // 시작점
                visited[i][j] = true // 시작점의 방문 여부를 true로 지정한다.

                /*
                    i = 0, j = 1 -> deque = [(0,1)], visited[0][1] = true
                        -> 4방향 탐색 결과 주변에 잔디 X -> count++ (count = 1)
                    i = 1, j = 2 -> deque = [(1,2)], visited[1][2] = true
                        -> grass[2][2]가 잔디(#) -> deque = [(2,2)], visited[1][2], visited[2][2] = true
                            -> 4방향 탐색 결과 주변에 잔디 X -> count++ (count = 2)
                    i = 2, j = 2 -> 이미 visited[2][2] = true이므로 탐색 진행 X
                    i = 2, j = 5 -> deque = [(2,5)], visited[2][5] = true
                        -> 4방향 탐색 결과 주변에 잔디 X -> count++ (count = 3)
                    i = 3, j = 4 -> deque = [(3,4)], visited[3][4] = true
                        -> grass[3][5]가 잔디(#) -> deque = [(3,5)], visited[3][4], visited[3][5] = true
                            -> 4방향 탐색 결과 주변에 잔디 X -> count++ (count = 4)
                    i = 3, j = 5 -> 이미 visited[3][5] = true이므로 탐색 진행 X
                    i = 4, j = 1 -> deque = [(4,1)], visited[4][1]
                        -> 4방향 탐색 결과 주변에 잔디 X -> count++ (count = 5)
                 */

                while (deque.isNotEmpty()) {
                    val (x, y) = deque.poll() // 기준 좌표
                    val fourDirectionList = listOf(Pair(0, 1), Pair(1, 0), Pair(-1, 0), Pair(0, -1)) // 4방향 탐색을 위한 리스트
                    var loopCount = 0 // 반복문 횟수를 측정

                    // 4방향 탐색 (diff: x와 y의 변화량)
                    for (diff in fourDirectionList) {
                        val nx = x + diff.first // 탐색하고자 하는 x좌표
                        val ny = y + diff.second // 탐색하고자 하는 y좌표
                        if (
                            (nx in 0..<row && ny + diff.second in 0..column) // 탐색하고자 하는 좌표의 유효성 여부 확인
                            && (grass[nx][ny] == '#') // 잔디(#) 여부 확인
                            && !visited[nx][ny] // 방문 여부 확인
                        ) {
                            visited[nx][ny] = true // 방문 체크
                            deque.add(Pair(nx, ny)) // 탐색할 위치 추가
                        } else loopCount++ // 조건문에 충족되지 않으면 loopCount 1 증가
                    }

                    // loopCount가 4인 경우 (= 조건문을 한 번도 충족되지 않은 경우) (= 주변에 잔디가 없는 경우)에 탐색을 종료하고 count++
                    if (loopCount == fourDirectionList.size) count++
                }
            }
        }
    }

    println(count)
}