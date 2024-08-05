package silver.q24431

import java.io.StreamTokenizer

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }
        fun string() = nextToken().run { sval }

        val numOfTest = int() // 테스트 케이스의 수

        buildString {
            repeat(numOfTest) {
                val numOfString = int() // 문자열의 개수
                val lengthOfString = int() // 문자열의 길이 (미사용)
                val step = int() // 공통 접미사의 길이 (F)
                val words = hashMapOf<String, Int>() // {라임: 개수}

                // 데이터 저장
                repeat(numOfString) {
                    val word = string()

                    // 공통 접미사만큼 자른(substring) 라임과, 그 개수를 words에 key, value로 각각 담는다.
                    word.substring(word.lastIndex - step + 1).let {
                        words[it] = words.getOrDefault(it, 0) + 1
                    }
                }

                // 적어도 한 쌍 이상의 조합을 만들 수 있는 라임의 개수를 '2로 나누어' 합친다. ('쌍'을 만들어야 하므로)
                append("${words.values.filter { it > 1 }.sumOf { it / 2 }}\n")
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