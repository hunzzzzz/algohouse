package silver.q9375

/*
    Ex) eyewear=[sunglasses], headgear=[hat, turban]

    각 종류(eyewear, headgear)를 입지 않는 경우도 계산에 포함해야 하기 때문에,
    즉, eyewear=[sunglasses, null], headgear=[hat, turban, null]라고 가정하고 푼다.

    위 조합의 경우의 수는 2 * 3이지만,
    eyewear와 headgear가 모두 null이면 알몸이 되기 때문에 -1을 해줘야 한다.
 */

import java.io.StreamTokenizer

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }
        fun string() = nextToken().run { sval }

        val fashion = HashMap<String, Int>() // {종류: 의상의 수}

        // 문자열 생성
        buildString {
            repeat(int()) {
                val numberOfClothes = int() // 의상 수
                var count = 1 // 옷 조합 경우의 수

                // HashMap에 의상 종류(key)와 해당 종류의 의상 수(value)를 저장한다.
                repeat(numberOfClothes) {
                    val value = string() // 의상 (불필요한 값)
                    val key = string() // 종류
                    if (fashion.containsKey(key)) fashion[key] = fashion[key]!! + 1 else fashion[key] = 1
                }

                // 해당 종류의 옷을 입지 않는 경우의 수도 포함시켜야 하기 때문에 1을 더하여 곱한다.
                fashion.values.forEach { count *= (it + 1) }
                fashion.clear()
                append("${count - 1}\n") // 모든 종류의 옷을 입지 않는 경우의 수를 빼준다.
            }
        }.let {
            // 문자열 출력
            System.out.bufferedWriter().use { writer ->
                writer.write(it)
                writer.flush()
            }
        }
    }
}