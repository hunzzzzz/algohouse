package silver.q13116

import java.io.StreamTokenizer

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }

        /*
            오른쪽으로 가면 1, 왼쪽으로 가면 0이라고 가정하자.

            33 = 100001
            79 = 1001111

            33과 79의 경로는 다음과 같다. 이 경로는 해당 숫자의 바이너리 값과 동일하다.
            따라서, 두 바이너리 값을 앞 자리부터 비교했을 때 일치하는 부분이 공통으로 포함되는 경로가 되는 것이다.
            결국, M(33,79) = 100(2진법) = 4가 된다.
         */

        buildString {
            repeat(int()) {
                val a = int().toString(2) // 첫번째 숫자를 2진법으로 변환한 문자열
                val b = int().toString(2) // 두번째 숫자를 2진법으로 변환한 문자열

                val shortest = if (a.length <= b.length) a else b // 길이가 짧은 문자열
                val intersect = StringBuilder() // 일치하는 경로를 담을 문자열

                // 두 바이너리 값을 비교
                for (i in shortest.indices) {
                    if (a[i] != b[i]) break
                    else intersect.append(a[i])
                }

                append("${intersect.toString().toInt(2) * 10}\n") // 다시 10진법으로 변환
            }

        }.let {
            with(System.out.bufferedWriter()) {
                write(it)
                flush()
                close()
            }
        }
    }
}