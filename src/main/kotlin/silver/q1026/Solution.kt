package silver.q1026

import java.io.StreamTokenizer
import java.util.*

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }

        val loopCount = int() // 반복문 횟수
        val queueA = PriorityQueue<Int>() // 우선순위 큐 A
        val queueB = PriorityQueue<Int>(Comparator.reverseOrder()) // 우선순위 큐 B
        var sum = 0 // S의 값

        // queueA와 queueB에 데이터를 대입한다.
        repeat(loopCount) { queueA.offer(int()) }
        repeat(loopCount) { queueB.offer(int()) }

        // queueA의 최상단 값(최소값)과 queueB의 최상단 값(최대값)을 곱한다.
        repeat(loopCount) { sum += queueA.poll() * queueB.poll() }

        // 문자열 생성 및 출력
        buildString { append("$sum\n") }.let {
            with(System.out.bufferedWriter()) {
                write(it)
                flush()
                close()
            }
        }
    }
}