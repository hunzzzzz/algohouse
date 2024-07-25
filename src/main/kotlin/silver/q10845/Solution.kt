package silver.q10845

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val queue: Queue<Int> = LinkedList() // 코틀린에서 Queue를 사용하는 방법
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    repeat(reader.readLine().toInt()) {
        reader.readLine().split(' ').let {
            // 명령어(it[0])에 따른 동작
            when (it[0]) {
                "push" -> queue.add(it[1].toInt())
                "pop" -> writer.write("${queue.poll() ?: -1}\n")
                "size" -> writer.write("${queue.size}\n")
                "empty" -> writer.write("${if (queue.isEmpty()) 1 else 0}\n")
                "front" -> writer.write("${queue.firstOrNull() ?: -1}\n")
                "back" -> writer.write("${queue.lastOrNull() ?: -1}\n")
                else -> Unit
            }
        }
    }

    reader.close()
    writer.flush()
    writer.close()
}