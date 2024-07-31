package silver.q1946

import java.io.StreamTokenizer

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }

        buildString {
            repeat(int()) {
                val numOfApplicants = int() // 지원자 수
                var count = 0 // 합격한 지원자 수
                val interviewRank = IntArray(numOfApplicants) // 면접 순위 데이터를 담을 배열

                /*
                    서류 순위는 1부터 n까지 중복이 없는 등차수열이다.
                    그렇기 때문에 [서류 순위 - 1]을 하면 배열의 인덱스가 되고, 서류 순위에 대한 오름차순이 인덱스를 통해 자동으로 적용된다.
                    따라서, 면접 순위만 비교해도 지원자의 합격/탈락 여부를 확인할 수 있다.
                 */
                repeat(numOfApplicants) { interviewRank[int() - 1] = int() }

                var highestInterviewRank = Int.MAX_VALUE // 가장 높은 면접 순위를 담을 변수

                // 면접 순위 비교
                interviewRank.forEach { interview ->
                    if (interview < highestInterviewRank) {
                        count++
                        highestInterviewRank = interview
                    }
                }

                append("${count}\n")
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