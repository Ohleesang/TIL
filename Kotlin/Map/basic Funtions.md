# Map 이라는 자료구조에서,기본적으로 쓰이는 함수들을 정리

 val map = mapOf("a" to 1,"b" to 2,"c" to 3)

1) getOrDafault
- 지정된 키에 대한 값이 존재하면 해당 값을 반환, 그렇지 않으면 기본 값을 반환
 ```kotlin
 var value = map.getOrDefault("d",0)
 //만약 "d" key 가 존재하지않으면 0을 반환
```
2) getOrElse
 - 지정된 키에 대한 값이 존재하면 해당 값을 반환, 그렇지 않으면 람다식 에 계산
 된 값을 반환
 ``` kotlin
 value = map.getOrElse("d"){ 0 }
 //만약 "d" key 가 존재하지 않으면 람다식 { 0 }에서 계산된 0을 반환
```
3) get,[]
 - 지정된 'key'에대한 'value'을 반환
 ``` kotlin
 value = map["a"]
 //"a" 키에 대한 값인 1을 반환
```
4) keys
 - 맵의 key들을 Set 형태로 반환
 ``` kotlin
 var keys = map.keys
 //[a, b, c]
```
5) values
 - 맵의 value들을 Collection 형태로 반환
 ``` kotlin
 var values = map.values
 //[1, 2, 3]
```
6) entries
 - 맵의 entries을 Set 형태로 반환, 각 entries는 키와 값의 쌍
``` kotlin
var entries = map.entries
//  [a=1, b=2, c=3] , 2차원 배열로 저장

 !!) 4,5,6 번 인자값은 배열의 indices처럼 인덱스 처리가 가능하다!
  ex) for(i in keys){
      print(i) 
    }// 123
```
7) filter
- 주어진 조건을 만족하는 entries로 이루어진 새로운 맵을 반환.
```kotlin
val filteredMap = map.filter{it.value > 1}
//값이 1보다 큰 엔트리들로 이루어진 맵 반환
```
8) map
- 각 entry에대해 주어진 변환 함수를 적용하여 새로운 값을 갖는 맵을 반환.
```kotlin
val mappedValues = map.map{it.value * 2}
// 각 값에 2를 곱한 값을 갖는 맵 반환
```
9) forEach
- 각 entry에 대해 주어진 동작을 수행
```kotlin
map.forEach { key, value ->
    println("Key: $key, Value: $value")
}
// 맵의 각 엔트리에 대해 키와 값을 출력
```
10) toMutableMap
- 현재 맵을 가변형 맵(toMutableMap)으로 변환
```kotlin
val mutableMap = map.toMutableMap()
//변경 가능한 Map 반환
```
# 결론
기존 자료구조 List 나 Array 와 달리 람다식이 들어가는 경우가 빈번해서 
따로 정리해놓았다. 자료 값을 이용하여 코드를 짜고싶을때 람다식을
이용하면 코드를 간결하게 짤 수있고 데이터를 빨리 읽을수있어
시간복잡도도 크게 줄어줄 수 있는 장점이있는 자료구조이다. 