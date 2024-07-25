package silver.q2503

/* 완전 탐색 (Brute Force) */

// ball, strike 여부를 판단하는 함수
fun baseball(answer: String, question: String): Map<String, Int> {
    var ballCount = 0
    var strikeCount = 0

    for (i in answer.indices) {
        if (answer[i] == question[i]) strikeCount++
        else if (answer[i] != question[i] && question.contains(answer[i])) ballCount++
    }

    return mapOf("ball" to ballCount, "strike" to strikeCount)
}

fun main() {
    val questionCount = readln().toInt() // 질문 개수
    val map = mutableMapOf<Int, Int>() // {숫자: 개수}

    repeat(questionCount) {
        val (question, strike, ball) = readln().split(' ')

        for (a in 1..9) { // 백의 자리 수
            for (b in 1..9) { // 십의 자리 수
                for (c in 1..9) { // 일의 자리 수
                    if (a == b || b == c || c == a) continue

                    // ball, strike 수가 동일한 경우 map에 추가 혹은 1 증가
                    baseball(answer = "$a$b$c", question = question).let {
                        if (ball.toInt() == it["ball"] && strike.toInt() == it["strike"])
                            map[100 * a + 10 * b + c] = map.getOrDefault(100 * a + 10 * b + c, 0) + 1
                    }
                }
            }
        }
    }

    println(map.values.count { it == questionCount })
}