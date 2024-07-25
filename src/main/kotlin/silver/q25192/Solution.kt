package silver.q25192

fun main() {
    var count = 0 // 곰곰티콘이 사용된 횟수
    val idSet = hashSetOf<String>() // 중복을 피하기 위해 id를 HashSet으로 저장

    // 첫 번째 입력값(채팅 수)만큼 반복
    repeat(readln().toInt()) {
        readln().let {
            // ENTER를 마주한 경우 idSet의 size를 count 더해주고, idSet 내 데이터 초기화
            if (it == "ENTER") {
                count += idSet.size
                idSet.clear()
            } else idSet.add(it) // ENTER가 아닌 경우 입력값(id)을 idSet에 대입
        }
    }

    // 마지막으로 idSet의 size를 한 번 더 더해줘야 한다
    count += idSet.size

    // 곰곰티콘이 사용된 횟수 출력
    println(count)
}