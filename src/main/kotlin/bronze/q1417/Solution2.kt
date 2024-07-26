package bronze.q1417

import java.util.*

fun main() {
    var count = 0 // 매수 횟수
    val numOfCandidates = readln().toInt() // 후보자의 수
    var votesForDasom = readln().toInt() // 다솜이의 득표수

    // 다른 후보자들의 득표수를 PriorityQueue에 저장한다. (우선순위: 내림차순)
    val votes = PriorityQueue<Int>(Comparator.reverseOrder())
    repeat(numOfCandidates - 1) { votes.offer(readln().toInt()) }

    while (true) {
        // 다솜이를 제외한 다른 후보자가 없거나, 다솜이가 나머지 후보자들보다 표가 많은 경우 반복문을 종료
        if (votes.isEmpty() || votesForDasom > votes.peek()) break

        // 다솜이의 득표수를 1 증가
        votesForDasom++

        // 다솜이를 제외한 최다 득표자의 득표수 1 감소
        votes.offer(votes.poll() - 1)

        // 매수 횟수 1 증가
        count++
    }

    println(count)
}