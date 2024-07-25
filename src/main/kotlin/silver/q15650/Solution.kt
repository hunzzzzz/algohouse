package silver.q15650

// 수열에 사용되는 필드를 정의해놓은 Object
object Sequence {
    var max: Int = 0 // 수열의 최대값
    var size: Int = 0 // 한 줄에 출력되는 수열의 크기
    val output: MutableList<Int> = mutableListOf() // 한 줄의 수열에 포함된 요소
}

// 재귀를 활용해 수열을 출력하는 함수
fun printSequence(depth: Int) {
    // [재귀 종료 조건] 수열의 길이를 만족하는 경우
    if (depth == Sequence.size) {
        println(Sequence.output.joinToString(" "))
        return
    }

    // 한 줄의 수열을 출력
    for (i in 1..Sequence.max) {
        // 중복이거나, (오름차순 정렬을 위해) output의 마지막 값보다 i가 작은 경우 더 이상 재귀 진행 X
        if (i in Sequence.output || (Sequence.output.size != 0 && i < Sequence.output.last())) continue
        Sequence.output.add(i)
        printSequence(depth = depth + 1)
        Sequence.output.removeLast()
    }
}

fun main() {
    // 최대값(max)과 수열의 길이(size)을 입력받아 Int형으로 변환
    readln().split(' ').map { it.toInt() }.let { (max, size) ->
        Sequence.max = max
        Sequence.size = size
        printSequence(depth = 0)
    }
}