package bronze.q1417

fun main() {
    var count = 0 // 매수하려는 사람의 수
    val numOfCandidates = readln().toInt() // 후보자의 수
    val votes = hashMapOf<Int, Int>() // {기호(Int): 득표수(Int)}
    repeat(numOfCandidates) { num -> votes[num + 1] = readln().toInt() }

    while (true) {
        val max = votes.values.maxOrNull()!! // 현재 최다 득표수
        val maxCandidates = votes.mapValues { it.value == max } // {기호(Int): 최다 득표 여부(Boolean)}

        // 다솜이가 유일한 최다 득표자일 때 반복문을 종료
        if (maxCandidates[1] == true && maxCandidates.values.count { it } == 1) break

        // 다솜이의 득표수를 1 증가
        votes[1] = votes[1]!! + 1
        // 다솜이를 제외한 최다 득표자의 득표수 1 감소
        votes.filter { (key, value) -> key != 1 && value == max }.map { it.key }.random()
            .let { votes[it] = votes[it]!! - 1 }
        count++
    }

    println(count)
}