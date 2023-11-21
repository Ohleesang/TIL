# associate ?
- Collection 의 각 요소에 대해 키와 값을 결정하여 맵을 생성한다.
이 함수는 고차 함수(higher-order function)으로,람다 식을 인자로 받는다. 
각 요소에 람다식을 실행하고,
그 결과 키(Key)와 값(Value)을 추출하여 맵을 생성한다.

fun <K, V> Iterable<T>,associate(transform: (T)->Pair<K, V>):Map<K, V>

@ '<K, V>' 는 키와 값의 타입 
@ 'Iterable<T>'는 함수를 호출하는 Collection 의 타입
@ 'transform'은 각 요소에 대해 호출되는 람다 식. 이 람다 식은 각 요소를 받아서
키와 값을 결정하여 'Pair<K, V>' 형태로 반환한다.

예제1) 문자열로 이루어진 List,각 문자열의 길이를 키로 가지는 맵으로 변환

val strList = listOf("apple","banana","grape")
val lengthMap = strList.associate { it to it.length }

@ it은 리스트의 각 문자열, it to it.length는 각 문자열을 키로 사용하고 
해당 문자열의 길이를 값으로 사용하여 맵을 생성.

예제2) 문자열 배열 이루어진 players, map으로 치환(값,순위)

val players = arrayOf("mumu", "soe", "poe", "kai", "mine")
var maps = players.associateBy{{it},{players.indexOf(it)+1}}.toMutableMap()

@ it은 players의 값 들을 키로, 각 players의 인덱스값+1으로 더한 값을 값으로가지는
가변형 맵 생성

# associate 관련 함수

1. associateBy

val players = arrayOf("mumu", "soe", "poe", "kai", "mine")
val playerMap = players.associateBy { it.length }
// 결과: {4=mumu, 3=soe, 3=poe, 3=kai, 4=mine}

2. associateWith

val players = arrayOf("mumu", "soe", "poe", "kai", "mine")
val playerMap = players.associateWith { it.length }
// 결과: {mumu=4, soe=3, poe=3, kai=3, mine=4}

3. associate

val players = arrayOf("mumu", "soe", "poe", "kai", "mine")
val playerMap = players.associate { it to it.length }
// 결과: {mumu=4, soe=3, poe=3, kai=3, mine=4}

@ 각 함수들을보면 람다 식에 따라 기준이 다르다는걸 알수있다. 
associate 의 경우는 {key to value} 두개의 값을 기준으로 들어가고
..By 의 경우는 Key 에 {람다 식} 의 값이 들어가고
..With 의 경우는 Value 에 {람다 식} 의 값이 들어간다.

4. associateTo

val players = arrayOf("mumu", "soe", "poe", "kai", "mine")
val existingMap = mutableMapOf<String, Int>()
players.associateTo(existingMap) { it to it.length }
// existingMap이 업데이트 됨: {mumu=4, soe=3, poe=3, kai=3, mine=4}

5. associateByTo

val players = arrayOf("mumu", "soe", "poe", "kai", "mine")
val existingMap = mutableMapOf<String, String>()
players.associateByTo(existingMap, { it }, { it.toUpperCase() })
// existingMap이 업데이트 됨: {mumu=MUMU, soe=SOE, poe=POE, kai=KAI, mine=MINE}

@ 4,5번 함수는 위의 예제와 다르게, 기존 맵에 요소를 추가하는데 사용된다.
각 람다식의 표현 방식이 다를 뿐 같은 기능을 한다.

