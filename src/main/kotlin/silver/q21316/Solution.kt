package silver.q21316

import java.io.StreamTokenizer

fun main() {
    StreamTokenizer(System.`in`.bufferedReader()).run {
        fun int() = nextToken().run { nval.toInt() }

        val stars = hashMapOf<Int, MutableList<Int>>()

        // 데이터 삽입
        repeat(12) {
            val start = int() // 시작 노드
            val end = int() // 종료 노드

            // 양방향 대입 (start->end, end->start의 경우를 모두 파악해야 한다.)
            stars.getOrPut(start) { mutableListOf() }.add(end)
            stars.getOrPut(end) { mutableListOf() }.add(start)
        }

        stars.filterValues { adjacent -> adjacent.size == 3 } // 인접 노드가 3개인지 판단
            .filterValues { adjacent ->
                adjacent.sumOf { node -> stars[node]?.size ?: 0 } == 6 // 인접 노드들의 인접 노드 수의 합이 6인지 판단
            }.keys.first().let {
                // 결과값 출력
                buildString { append("$it\n") }.let {
                    with(System.out.bufferedWriter()) {
                        write(it)
                        flush()
                        close()
                    }
                }
            }
    }
}