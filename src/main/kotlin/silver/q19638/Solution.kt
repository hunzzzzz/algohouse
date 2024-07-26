package silver.q19638

import java.util.*

fun main() {
    val (numOfGiants, heightOfCenti, hitMax) = readln().split(' ').map { it.toInt() }
    var hitCount = 0 // 뿅망치를 때린 횟수

    // 거인들의 키를 PriorityQueue에 저장한다. (우선순위: 내림차순)
    val heights = PriorityQueue<Int>(Comparator.reverseOrder())
    repeat(numOfGiants) { heights.offer(readln().toInt()) }

    while (true) {
        // 센티가 나머지 거인들보다 키가 큰 경우 프로그램을 종료(return)
        if (heightOfCenti > heights.peek()) {
            println("YES\n${hitCount}")
            return
        }

        // 뿅망치 횟수를 모두 소진하거나, 최장신 거인의 키가 1인 경우, 반복문을 종료(break)
        else if (hitCount == hitMax || heights.peek() == 1) break

        // 센티를 제외한 최장신 거인을 뿅망치로 때린다.
        heights.offer(heights.poll() / 2)

        hitCount++
    }

    println("NO\n${heights.peek()}") // 센티를 제외한 최장신 거인의 키
}