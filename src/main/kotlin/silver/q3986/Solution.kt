package silver.q3986

/*
    두 개의 Stack을 활용한다.
    leftStack에 사용자가 입력한 문자열의 모든 글자를 담고, 최상단 값부터 하나씩 rightStack으로 옮기는데,
    그 과정에서 rightStack의 최상단 두 요소가 같으면 해당 요소를 제거한다.

    반복문이 종료되었을 때, leftStack과 rightStack이 모두 비어있으면, 해당 문자열은 좋은 단어가 된다.

    Ex) ABBABB
          -> ABBAB | B
          -> ABBA | BB -> ABBA |
          -> ABB | A
          -> AB | BA
          -> A | BBA -> A | A
          -> | AA -> |
          ∴ ABBABB는 좋은 단어
 */

import java.util.*

fun main() {
    var count = 0 // 좋은 단어의 수

    for (notUsed in 1..readln().toInt()) {
        val leftStack = Stack<Char>().let {
            readln().toMutableList().forEach { ch -> it.push(ch) }
            it
        }
        val rightStack = Stack<Char>()

        repeat(leftStack.size) {
            // leftStack의 최상단 값을 rightStack으로 이동
            rightStack.push(leftStack.pop())
            // rightStack의 두 최상단 요소를 비교
            if (rightStack.getOrNull(rightStack.size - 1) == rightStack.getOrNull(rightStack.size - 2))
                repeat(2) { rightStack.pop() }
        }

        // leftStack과 rightStack이 모두 비어있으면 좋은 단어
        if (leftStack.size == 0 && rightStack.size == 0)
            count++
    }

    println(count)
}