package bronze.q17608

import java.util.*

fun main() {
    val numOfSticks = readln().toInt() // 막대기 개수
    val heights = Stack<Int>() // 스틱의 높이를 저장할 Stack

    // 사용자 입력값(막대기 높이)를 Int형으로 변환하여 Stack에 저장
    repeat(numOfSticks) { heights.add(readln().toInt()) }

    var max = 0 // 탐색한 막대기 중 가장 높은 막대기의 높이
    var count = 0 // 보이는 막대기의 수

    repeat(numOfSticks) {
        // Stack에서 하나씩 꺼내면서(pop) 해당 값을 비교
        heights.pop().let {
            if (it > max) {
                count++ // 막대기 높이가 max보다 커야 보이므로, count에 1 증가
                max = it // max 값 변경
            }
        }
    }

    println(count)
}