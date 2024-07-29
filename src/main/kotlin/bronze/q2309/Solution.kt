package bronze.q2309

import java.io.StreamTokenizer

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }

        val heights = IntArray(9) { int() } // 난쟁이들의 키를 담은 배열
        val totalHeights = heights.sum() // 난쟁이 9명의 키의 합

        // 배열을 오름차순 정렬한다.
        heights.sort()

        /*
            가짜 난쟁이 2명의 키의 합은, 난쟁이 9명의 키의 합에서 100을 뺀 값과 같다.
            따라서, 가짜 난쟁이 1명의 키를 알게되면,
            [난쟁이 9명의 키의 합] - 100 - [가짜 난쟁이 1명의 키] 식으로 나머지 가짜 난쟁이 1명의 키를 알 수 있다.
         */
        for (i in heights.indices) {
            for (j in i + 1..heights.lastIndex) {
                // 가짜 난쟁이 2명을 발견하면, 해당 난쟁이의 키를 0으로 만들고 그 즉시 프로그램을 종료한다.
                if (totalHeights - heights[i] - heights[j] == 100) {
                    heights[i] = 0
                    heights[j] = 0

                    // 결과 출력
                    with(System.out.bufferedWriter()) {
                        heights.forEach { if (it != 0) append("${it}\n") }
                        flush()
                        close()
                        return
                    }
                }
            }
        }
    }
}