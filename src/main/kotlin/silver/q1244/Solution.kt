package silver.q1244

fun main() {
    // 사용자 입력값(스위치 개수)을 Int형으로 변환
    val switchSize = readln().toInt()

    // 사용자 입력값(현재 스위치 상태)을 공백으로 구분 후 리스트에 담고, 모든 요소를 Boolean형으로 변환 (1: True, 2: False)
    val switch = readln().split(' ').map { it.toInt() }.map { it > 0 }.toMutableList()

    // 사용자 입력값(학생 수)을 Int형으로 변환하고, 해당 수만큼 아래 로직을 반복
    repeat(readln().toInt()) {
        // 사용자 입력값(성별, 숫자)를 공백으로 구분 후 리스트에 담아 Int형으로 변환
        readln().split(' ').map { it.toInt() }.let { (gender, n) ->
            when (gender) {
                // 남자
                1 -> {
                    // n 스위치 변경
                    switch[n - 1] = !switch[n - 1]
                    // n의 배수 스위치 변경 (반복문은 2배수부터 [스위치 개수 / n]배수까지 진행)
                    // Ex. 스위치 개수가 16개이고, n이 3이라면, 16 / 3 = 5이므로, 2부터 5배수까지, 총 4번의 반복문이 돈다.
                    (2..switchSize / n).forEach { i ->
                        if (switch.getOrNull(n * i - 1) != null) switch[n * i - 1] = !switch[n * i - 1]
                    }
                }

                2 -> {
                    // n 스위치 변경
                    switch[n - 1] = !switch[n - 1]
                    // 대칭 스위치 변경 여부 탐색 (반복문은 n 기준 head와 tail까지 거리 절대값 중 짧은 값만큼 진행)
                    // Ex. 스위치 개수가 16개이고, n이 3이라면, min(16-3, 3-1)=2이므로, 최대 2번의 탐색을 진행
                    for (i in 1..minOf(switchSize - n, n - 1)) {
                        if (switch.getOrNull(n - i - 1) != null && switch.getOrNull(n + i - 1) != null) {
                            // 대칭인 경우에만 양 스위치 변경
                            if (switch[n - i - 1] == switch[n + i - 1]) {
                                switch[n - i - 1] = !switch[n - i - 1]
                                switch[n + i - 1] = !switch[n + i - 1]
                            } else break // 대칭이 아닌 경우 반복문 종료
                        }
                    }
                }
            }
        }
    }

    // 한 줄에 20개의 스위치씩 출력
    switch.map { if (it) 1 else 0 }.chunked(20).forEach { println(it.joinToString(" ")) }
}