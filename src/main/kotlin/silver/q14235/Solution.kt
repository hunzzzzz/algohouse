package silver.q14235

import java.io.StreamTokenizer
import java.util.*

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() } // 정수를 입력받는 함수

        val gift = PriorityQueue<Int>(Comparator.reverseOrder()) // 선물들의 가치를 저장하는 우선순위 큐 (내림차순)

        // 문자열 생성
        buildString {
            // 첫 번째 입력값(방문 횟수)만큼 반복문을 수행
            repeat(int()) {
                // 명령어에 따른 수행
                when (val condition = int()) {
                    0 -> append("${if (gift.isEmpty()) -1 else gift.poll()}\n")
                    else -> repeat(condition) { gift.offer(int()) }
                }
            }
        }.let {
            // 문자열 출력
            System.out.bufferedWriter().use { writer ->
                writer.write(it)
                writer.flush()
            }
        }
    }
}