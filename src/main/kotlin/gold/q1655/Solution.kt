package gold.q1655

import java.io.StreamTokenizer
import java.util.*

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }

        val smallQueue = PriorityQueue<Int>(Comparator.reverseOrder()) // 작은 수들을 담을 우선순위 큐
        val largeQueue = PriorityQueue<Int>() // 큰 수들을 담을 우선순위 큐

        // 문자열 생성
        buildString {
            repeat(int()) {
                val inputNumber = int() // 백준이가 외치는 정수

                // 첫 정수는 smallQueue에 담는다.
                if (it == 0) smallQueue.offer(inputNumber)
                else {
                    // 정수가 smallQueue의 최대값보다 크거나 같으면, 일단 largeQueue에 저장한다.
                    if (smallQueue.peek() <= inputNumber) largeQueue.offer(inputNumber)
                    else {
                        // 정수가 smallQueue의 최대값보다 작으면, smallQueue에 저장한다.
                        // 기존 smallQueue의 최대값을 꺼내(poll) largeQueue에 저장한다.
                        largeQueue.offer(smallQueue.poll())
                        smallQueue.offer(inputNumber)
                    }
                }

                // smallQueue와 largeQueue의 크기는 같거나, 하나 차이를 유지해야 한다.
                if (largeQueue.size - smallQueue.size > 1)
                    smallQueue.offer(largeQueue.poll())
                else if (smallQueue.size - largeQueue.size > 1)
                    largeQueue.offer(smallQueue.poll())

                // 짝수인 경우 smallQueue의 최대값을 출력
                if ((it + 1) % 2 == 0)
                    append("${smallQueue.peek()}\n")
                // 홀수인 경우 smallQueue와 largeQueue 중 사이즈가 더 큰 Queue의 최대값(혹은 최소값)을 출력
                else
                    append("${if (largeQueue.size > smallQueue.size) largeQueue.peek() else smallQueue.peek()}\n")
            }
        }.let {
            // 문자열 출력
            with(System.out.bufferedWriter()) {
                append(it)
                flush()
                close()
            }
        }
    }
}