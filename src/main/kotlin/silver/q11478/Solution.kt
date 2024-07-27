package silver.q11478

/*
    Ex) str이 ababc인 경우,
        step = 1 ->
            "a" (str.substring(0, 1)), "b" (str.substring(1, 2)),
            "a" (str.substring(2, 3)), "b" (str.substring(3. 4)), "c" (str.substring(4, 5))
        step = 2 ->
            "ab" (str.substring(0, 2)), "ba" (str.substring(1, 3)),
            "ab" (str.substring(2, 4)), "bc" (str.substring(3, 5))
        step = 3 ->
            "aba" (str.substring(0, 3)), "bab" (str.substring(1, 4)), "abc" (str.substring(2, 5))
        step = 4 ->
            "abab" (str.substring(0, 4)), "babc" (str.substring(1, 5))
        step = 5 ->
            "ababc" (str.substring(0, 5))
 */

fun main() {
    val str = readln() // 입력받은 문자열
    val strSet = HashSet<String>() // 부분 문자열을 저장할 HashSet
    var count = 0 // 부분 문자열의 개수

    // step = substring 시 잘라낼 문자열의 크기
    (1..str.length).forEach { step ->
        // substring 시 인덱스를 초과하지 않게끔 length - step까지만 반복문을 돌린다.
        for (index in 0..str.length - step)
            strSet.add(str.substring(index, index + step))

        count += strSet.size
        strSet.clear()
    }

    println(count)
}