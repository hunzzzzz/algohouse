package bronze.q16466

import java.io.StreamTokenizer
import java.util.*

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }

        val N = int() // 현재까지 팔린 티켓의 개수
        val tickets = PriorityQueue<Int>() // 현재까지 팔린 티켓의 번호

        /*
            현재까지 팔린 티켓의 개수(N)보다 큰 번호의 티켓은 tickets에 담을 필요가 없다.
            어차피 양한이가 가질 수 있는 티켓의 가장 작은 번호는 1부터 N+1 사이에서 나오기 때문이다.

            예를 들어, N=5이고, 1차 티켓팅에서 판매된 티켓 번호가 4, 1, 2, 7, 8라고 가정하자.
            tickets에 1,2,4만 담아주어도, 양한이가 가질 수 있는 티켓의 가장 작은 번호 3을 계산할 수 있다.

            1차 티켓팅에서 번호가 1부터 5까지인 모든 티켓이 판매된 '최악의 경우'에도
            양한이가 가질 수 있는 티켓의 가장 작은 번호 6(N+1)을 계산할 수 있다.
         */

        // 번호가 N 이하인 티켓만 담는다.
        repeat(N) { int().let { if (it <= N) tickets.offer(it) } }

        // 문자열 생성
        buildString {
            // tickets이 비어있거나 첫 번째 값이 1이 아니면, 1을 문자열에 담는다.
            if (tickets.isEmpty() || tickets.peek() != 1) append("1\n")
            // N이 tickets의 크기와 같으면(=최악의 경우), N+1을 문자열에 담는다.
            else if (N == tickets.size) append("${N + 1}\n")
            // 1부터 탐색을 시작해서, 없는 번호가 등장하는 순간 문자열에 담고 탐색을 종료한다.
            else {
                for (num in 1..tickets.size) {
                    if (num != tickets.poll()) {
                        append("${num}\n")
                        break
                    }
                }
            }
        }.let {
            // 문자열 출력
            with(System.out.bufferedWriter()) {
                write(it)
                flush()
                close()
            }
        }
    }
}