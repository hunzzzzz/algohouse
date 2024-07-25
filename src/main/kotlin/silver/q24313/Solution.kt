package silver.q24313

// f(n) 함수 정의
fun f(a0: Int, a1: Int, n: Int) = a1 * n + a0

// g(n) 함수 정의
fun g(n: Int) = n

fun main() {
    /*
        O(g(n)) = {f(n) | 모든 n ≥ n0에 대하여 f(n) ≤ c × g(n)인 양의 상수 c와 n0가 존재한다}
            -> O(n) 정의를 만족하는지 여부를 판단해야 하므로, g(n) = n이라 두고 풀이를 진행한다.
     */

    // 함수에 필요한 값들을 입력받아 Int형으로 변환
    val (a1, a0) = readln().split(' ').map { it.toInt() }
    val c = readln().toInt()
    val n0 = readln().toInt()

    /*
        g(n)의 기울기 c와 n의 최소값(n0)이 모두 양수이기 때문에,
        n0에 대한 f(n) <= c * g(n)이 참을 만족하면, n에 n+1, n+2, ..., n+∞을 대입해도 항상 참이 된다.
        따라서, f(n0) <= c * g(n0)의 참/거짓 여부만 판단하면 된다.

        단, 위 경우에는 반례가 존재한다.
        f(n)의 기울기가 g(n)의 기울기보다 큰 경우,
        f(n0) <= c * g(n0)을 만족해도 언젠가는 f(n)이 g(n)을 따라잡는 n이 생긴다.
     */
    println(
        // [반례] f(n)의 기울기가 g(n)의 기울기보다 큰 경우
        if (c < a1) 0
        // n0에 대한 f(n) <= c * g(n) 만족 여부 확인
        else if (f(a0 = a0, a1 = a1, n = n0) <= c * g(n = n0)) 1 else 0
    )
}