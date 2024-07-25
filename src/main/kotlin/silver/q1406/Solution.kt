package silver.q1406

// [Point] 커서를 기준으로 왼쪽에 있는 요소들을 leftStack, 오른쪽에 있는 요소들을 rightStack에 담는다.
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val leftStack = Stack<Char>().let {
        reader.readLine().forEach { ch -> it.push(ch) }
        it
    } // 커서 왼쪽
    val rightStack = Stack<Char>() // 커서 오른쪽

    repeat(reader.readLine().toInt()) {
        reader.readLine().split(' ').let {
            // 명령어(it[0])에 따른 동작
            when (it[0]) {
                "L" -> if (leftStack.isNotEmpty()) rightStack.push(leftStack.pop()) else Unit
                "D" -> if (rightStack.isNotEmpty()) leftStack.push(rightStack.pop()) else Unit
                "B" -> if (leftStack.isNotEmpty()) leftStack.pop()
                "P" -> leftStack.push(it[1][0])
                else -> Unit
            }
        }
    }

    // 현재 leftStack과 rightStack이 ⊂ ⊂ 모양이므로, 출력 시에는 ⊂ ⊃ 모양으로 변형해줘야 한다.
    println("${leftStack.joinToString("")}${rightStack.joinToString("").reversed()}")

    reader.close()
}