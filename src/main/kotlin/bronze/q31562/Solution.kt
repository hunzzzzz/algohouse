package bronze.q31562

fun main() {
    val (numOfSong, numOfQuiz) = readln().split(' ').map { it.toInt() } // 알고 있는 음악 수, 퀴즈 수
    val songs = hashMapOf<String, List<String>>() // {제목(String): 계이름(List<Char>)}

    // songs에 알고 있는 음악의 제목과 계이름을 대입
    repeat(numOfSong) {
        readln().split(' ').let {
            songs[it[1]] = it.subList(2, it.lastIndex + 1)
        }
    }

    // 퀴즈 풀기
    repeat(numOfQuiz) {
        readln().split(' ').let { quiz ->
            // 첫 세 음이 동일한 노래의 개수로 출력값을 구분
            songs.filterValues { value -> value.subList(0, 3) == quiz }.let {
                println(
                    if (it.size == 1) it.keys.first()
                    else if (it.size > 1) "?"
                    else "!"
                )
            }
        }
    }
}