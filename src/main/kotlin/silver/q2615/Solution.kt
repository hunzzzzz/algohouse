package silver.q2615

fun main() {
    val list = mutableListOf<MutableList<Int>>()

    // 사용자 입력값(오목판 현황)을 받아 공백으로 구분 후 list에 대입
    repeat(19) { list.add(readln().split(' ').map { it.toInt() }.toMutableList()) }

    for (i in list.indices) {
        if (list[i].sum() == 0) continue // 가로줄 안에 돌이 존재하지 않는 경우 탐색 진행 X

        for (j in list[i].indices) {
            if (list[i][j] == 0) continue // 돌이 0인 경우 탐색 진행 X

            // 가로 5줄로 승리하는 경우
            for (k in (1..4)) {
                if (j > list[i].size - 5) break // 기준 돌 우측에 돌이 4개 이상 존재하지 않으면 탐색 진행 X
                else if (list[i][j + k] != list[i][j]) break // 우측 돌이 기준 돌과 다른 경우 탐색 진행 X
                // 가로 5줄이 완성된 경우
                if (k == 4) {
                    if (
                        list[i].getOrNull(j - 1) == list[i][j]
                        || list[i].getOrNull(j + 5) == list[i][j]
                    ) break // 가로 6줄인 경우를 검증
                    println(list[i][j])
                    println("${i + 1} ${j + 1}")
                    return
                }
            }

            // 세로 5줄로 승리하는 경우
            for (k in (1..4)) {
                if (i > list.size - 5) break // 기준 돌 하위에 돌이 4개 이상 존재하지 않으면 탐색 진행 X
                else if (list[i + k][j] != list[i][j]) break // 하위 돌이 기준 돌과 다른 경우 반복문 탈출
                // 세로 5줄이 완성된 경우
                if (k == 4) {
                    if (
                        list.getOrNull(i - 1)?.getOrNull(j) == list[i][j]
                        || list.getOrNull(i + 5)?.getOrNull(j) == list[i][j]
                    ) break // 세로 6줄인 경우를 검증
                    println(list[i][j])
                    println("${i + 1} ${j + 1}")
                    return
                }
            }

            // 하향 대각선 5줄 승리
            for (k in (1..4)) {
                if (i > list.size - 5 || j > list[i].size - 5) break // 기준 돌 우측과 하위에 돌이 4개 이상 존재하지 않으면 탐색 진행 X
                else if (list[i + k][j + k] != list[i][j]) break // 대각선 돌이 기준 돌과 다른 경우 반복문 탈출
                // 대각선 5줄이 완성된 경우
                if (k == 4) {
                    if (list.getOrNull(i - 1)?.getOrNull(j - 1) == list[i][j]
                        || list.getOrNull(i + 5)?.getOrNull(j + 5) == list[i][j]
                    ) break // 대각선 6줄인 경우를 검증
                    println(list[i][j])
                    println("${i + 1} ${j + 1}")
                    return
                }
            }

            // 상향 대각선 5줄 승리
            for (k in (1..4)) {
                if (i < 4 || j > list[i].size - 5) break // 기준 돌 우측과 상위에 돌이 4개 이상 존재하지 않으면 탐색 진행 X
                else if (list[i - k][j + k] != list[i][j]) break // 대각선 돌이 기준 돌과 다른 경우 반복문 탈출
                // 대각선 5줄이 완성된 경우
                if (k == 4) {
                    if (list.getOrNull(i + 1)?.getOrNull(j - 1) == list[i][j]
                        || list.getOrNull(i - 5)?.getOrNull(j + 5) == list[i][j]
                    ) break // 대각선 6줄인 경우를 검증
                    println(list[i][j])
                    println("${i + 1} ${j + 1}")
                    return
                }
            }
        }
    }

    println(0) // 누구의 승리도 아닌 경우 0을 출력
}