package silver.q10866

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val deque = LinkedList<Int>() // 코틀린에서 Deque를 사용하는 방법
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    repeat(reader.readLine().toInt()) {
        reader.readLine().split(' ').let {
            // 명령어(it[0])에 따른 동작
            when (it[0]) {
                "push_front" -> deque.addFirst(it[1].toInt())
                "push_back" -> deque.addLast(it[1].toInt())
                "pop_front" -> writer.write("${deque.removeFirstOrNull() ?: -1}\n")
                "pop_back" -> writer.write("${deque.removeLastOrNull() ?: -1}\n")
                "size" -> writer.write("${deque.size}\n")
                "empty" -> writer.write("${if (deque.isEmpty()) 1 else 0}\n")
                "front" -> writer.write("${deque.firstOrNull() ?: -1}\n")
                "back" -> writer.write("${deque.lastOrNull() ?: -1}\n")
            }
        }
    }

    reader.close()
    writer.flush()
    writer.close()
}