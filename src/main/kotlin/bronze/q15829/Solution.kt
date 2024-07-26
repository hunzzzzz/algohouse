package bronze.q15829

object Hash {
    const val HASH_NUM = 31
    const val MOD = 1234567891
}

/*
    Math.pow (x.pow(n)) 메서드는 x와 n을 Double 형으로 전달받기 때문에 수가 커질수록 정밀한 계산이 어렵다.
    따라서, 정수형으로 제곱값을 계산하는 함수를 임의로 만든다.
 */

// x의 n 제곱 값을 계산하는 함수
fun pow(x: Long, n: Int): Long {
    return if (n == 0) 1
    else x * pow(x, n - 1) % Hash.MOD
}

// [POINT] 수가 커질 가능성이 있는 모든 구간에 mod 연산을 진행한다.
fun main() {
    val loopCount = readln().toInt()
    val str = readln()
    var hash = 0L

    repeat(loopCount) { i ->
        hash += (pow(Hash.HASH_NUM.toLong(), i) * (str[i].code - 96)) % Hash.MOD
    }

    println(hash % Hash.MOD)
}