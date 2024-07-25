package silver.q10828

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val stack = Stack<Int>() // Stack 자료구조 사용
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    repeat(reader.readLine().toInt()) {
        reader.readLine().split(' ').let {
            // 명령어(it[0])에 따른 동작
            when (it[0]) {
                "push" -> stack.push(it[1].toInt())
                "pop" -> writer.write("${stack.removeLastOrNull() ?: -1}\n")
                "size" -> writer.write("${stack.size}\n")
                "empty" -> writer.write("${if (stack.isEmpty()) 1 else 0}\n")
                "top" -> writer.write("${stack.lastOrNull() ?: -1}\n")
            }
        }
    }

    reader.close()
    writer.flush()
    writer.close()
}