package gold.q2143

import java.io.StreamTokenizer

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() } // 정수를 입력받는 함수

        val T = int() // T의 값
        val listA = IntArray(int()) { int() } // 배열 A
        val listB = IntArray(int()) { int() } // 배열 B

        /*
            부분합은 절대로 Int 범위를 벗어나지 않는다. (최대값 = 1,000,000 * 1,000 = 1,000,000,000)
            하지만, 개수는 Int 범위를 벗어날 수 있다.
            예를 들어, T = 0, N1 = 1000, ListA = [0, 0, 0, ..., 0], N2 = 1000, ListB = [0, 0, 0, ..., 0]인 경우에는
            (1000 + 999 + ... + 1) * (1000 + 999 + ... + 1)
              = 500,500 * 500,500 = 250,500,250,000 가지의 경우의 수를 가진다.
         */
        val sumOfA = hashMapOf<Int, Long>() // {부분합: 개수}

        // sumOfA에 부분합들의 조합을 전부 담는다.
        repeat(listA.size) { i ->
            var sum = 0
            for (j in i until listA.size) {
                sum += listA[j]
                sumOfA[sum] = (sumOfA[sum] ?: 0) + 1
            }
        }

        var count = 0L // 경우의 수

        // sumOfB의 부분합들을 위와 같은 방법으로 계산한다.
        repeat(listB.size) { i ->
            var sum = 0
            for (j in i until listB.size) {
                sum += listB[j]
                count += sumOfA.getOrDefault(T - sum, 0) // A의 누적합 = T - B의 누적합(it)
            }
        }

        println(count)
    }
}