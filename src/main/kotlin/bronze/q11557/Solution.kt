package bronze.q11557

import java.io.StreamTokenizer

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }
        fun string() = nextToken().run { sval }

        // 문자열 생성
        buildString {
            repeat(int()) {
                var university = Pair("", 0) // first: 학교 이름, second: 소비량

                repeat(int()) {
                    val nameOfUniversity = string() // 학교 이름
                    val consumption = int() // 소비량

                    // 소비량이 제일 많은 대학교를 선별
                    if (university.second < consumption) university = Pair(nameOfUniversity, consumption)
                }

                append("${university.first}\n")
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