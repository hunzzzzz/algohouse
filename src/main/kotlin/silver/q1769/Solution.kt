package silver.q1769

// 3의 배수인지 판단하는 함수
fun isMultipleOf3(n: Int) = n % 3 == 0

// [Point] X는 최대 1,000,000자리이므로, toInt나 toLong을 사용해서 Number 형식으로 변환할수 없다.
fun main() {
    var loopCount = 0 // 반복문 횟수 (변환 과정 횟수)
    var num = readln() // 변환 과정을 거친 수

    // num이 한 자리수가 될 때까지 반복문을 실행
    while (num.length != 1) {
        loopCount++
        num = num.toMutableList().sumOf { it.digitToInt() }.toString() // 각 자리의 수를 모두 더한다.
    }

    println(loopCount)
    println(if (isMultipleOf3(num.toInt())) "YES" else "NO")
}