# forEach 반복문
### 컬렉션(리스트,배열 등)의 각 요소에 대해 작업을 수행하는데 사용!
기존 for문보다 간결하고 가독성이 좋은 반복문의 형태를 제공!

```kotlin
collection.forEach { element ->
    // 각 요소에 대한 작업 수행
    // element 변수를 통해 현재 요소에 접근 가능
}
```
- collection :  반복하고자 하는 컬렉션(리스트,배열)
- element : 현재 요소에 대한 참조(기본값 : it)

## 주의사항
forEach 문 사용할때 주의해야 할 상황이 있다.

#### 1. 컬렉션이 null 일경우.
기본적으로 컬렉션이 null 이어선 안된다.
> NullPointerException

```kotlin
val numbers: List<Int>? = null

numbers?.forEach { number ->
    // ...
}
```

### 2. 컬렉션을 수정하는경우
forEach 로 반복중에 컬렉션을 수정하려고 하면 오류가 발생한다!
> ConcurrentModificationException
 새로운 컬렉션을 만들어서 처리하거나 대신에 Iterator를 사용하자.
- Iterator : 컬렉션을 순회하면서 각 요소에 접근할때 사용되는 인터페이스
    -  next(),hasNext()

```kotlin
//forEach 의경우
val numbers = mutableListOf(1, 2, 3, 4)

numbers.forEach { number ->
    // 리스트를 수정하면 예외 발생
    numbers.remove(number) // 에러 발생
}

//iterator의 경우
val iterator = numbers.iterator()
while (iterator.hasNext()) {
    val number = iterator.next()
        iterator.remove()
}
```

#### 3. return 사용시 주의
forEach 내 return을 사용하면 해당 람다 함수만 종료하고 반복하기 떄문에 

명시적으로 지정해줘야한다.(return@forEach)

```kotlin
val numbers = listOf(1, 2, 3, 4, 5)

numbers.forEach {
    if (it == 3) {
        return@forEach // 레이블을 사용하여 forEach 자체를 종료
    }
    println(it)
}

```

#### 4.안전한 호출(?.)
컬렉션 내에 null값이 있을경우 '?.'를 사용하여 방지해야한다.
>NullPointerException
```kotlin
val nullableNumbers: List<Int?> = listOf(1, 2, null, 4)

nullableNumbers.forEach { number ->
    // null 체크를 통한 동작 정의
    number?.let {
        println(it)
    }
}
```

# 결론
forEach 강력한 문법이다. 하지만 막상 index값을 이용하는 알고리즘 문제나, 동적으로 값을 고치는 경우에는 적합하지않다. 즉 말 그대로 자료를 순회할때에 쓰이거나,그 순회중에 무언가를 처리할때 쓰는게 가장 좋은거같다.