package silver.q1181

import java.io.StreamTokenizer

// 정렬 조건을 직접 제어한다.
class StringComparator : Comparator<String> {
    override fun compare(str1: String, str2: String) =
        if (str1.length == str2.length) str1.compareTo(str2)
        else str1.length - str2.length
}

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }
        fun string() = nextToken().run { sval }

        val treeSet = sortedSetOf(StringComparator()) // 문자열들을 담을 TreeSet (정렬 조건 추가)
        repeat(int()) { treeSet.add(string()) }

        buildString {
            treeSet.forEach { append("$it\n") }
        }.let {
            // 문자열 출력
            with(System.out.bufferedWriter()) {
                write(it)
                flush()
                close()
            }
        }
    }
}