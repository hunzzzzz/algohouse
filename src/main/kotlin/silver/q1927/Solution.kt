package silver.q1927

import java.io.StreamTokenizer
import java.util.*

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() } // 정수를 입력받는 함수

        val queue = PriorityQueue<Int>() // 우선순위 큐

        // 문자열 생성
        buildString {
            // 첫 번째 입력값(연산 개수)만큼 반복문 수행
            repeat(int()) {
                when (val input = int()) {
                    // 명령어에 따른 수행
                    0 -> append("${if (queue.isEmpty()) 0 else queue.poll()}\n")
                    else -> queue.offer(input)
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