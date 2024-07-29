package bronze.q1524

import java.io.StreamTokenizer

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }

        // 문자열 생성
        buildString {
            repeat(int()) {
                // 세준을 A, 세비를 B로 표현한다.
                val numOfASoldiers = int() // 세준의 병사 수
                val numOfBSoldiers = int() // 세비의 병사 수
                var strongestInA = Int.MIN_VALUE // 세준의 병사들 중 최대 전투력
                var strongestInB = Int.MIN_VALUE // 세비의 병사들 중 최대 전투력

                /*
                    전쟁은 마지막 한 명의 병사가 남을 때까지 진행된다.
                    따라서, 마지막에 남는 병사의 전투력은 세준과 세비의 모든 병사들 중 최대일 것이다.
                    최대 전투력 병사의 소속 여부가 결국 이 전쟁의 승/패 여부를 결정한다.
                 */

                // 세준과 세비의 각 병사들의 전투력 중 최대값을 찾는다.
                repeat(numOfASoldiers) { int().let { input -> if (input > strongestInA) strongestInA = input } }
                repeat(numOfBSoldiers) { int().let { input -> if (input > strongestInB) strongestInB = input } }

                // 최대값 비교
                append("${if (strongestInA >= strongestInB) "S" else "B"}\n")
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