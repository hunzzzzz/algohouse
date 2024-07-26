package bronze.q9933

fun main() {
    val passwords = hashSetOf<String>() // 비밀번호 후보군을 저장할 HashSet

    repeat(readln().toInt()) {
        readln().let {
            // 문자열을 뒤집어 HashSet에 저장
            passwords.add(it.reversed())

            // 사용자가 입력한 문자열이 HashSet에 있으면 결과값을 출력하고 종료
            if (it in passwords) {
                println("${it.length} ${it[it.length / 2]}")
                return
            }
        }
    }
}