package bronze.q12605

fun main() {
    repeat(readln().toInt()) {
        /*
            사용자 입력값(문자열)에 대해 다음과 같은 과정을 거친다.
                1. 공백으로 구분해 리스트로 담는다. (split)
                2. 뒤집는다. (reversed)
                3. 다시 문자열로 합친다. 이 때, 각 요소 사이에 공백(" ")을 삽입한다. (joinToString)
         */
        println("Case #${it + 1}: ${readln().split(' ').reversed().joinToString(" ")}")
    }
}