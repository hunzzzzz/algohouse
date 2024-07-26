package silver.q1927

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val queue = PriorityQueue<Int>() // 우선순위 큐

    repeat(reader.readLine().toInt()) {
        when (val input = reader.readLine().toInt()) {
            // 명령어(input)에 따른 동작
            0 -> writer.write("${if (queue.isEmpty()) 0 else queue.poll()}\n")
            else -> queue.offer(input)
        }
    }

    reader.close()
    writer.close()
}