# Stack(스택)
### 후입 선출(Last In First Out) 특성을 가지는 자료구조

>쌓여있는 팬케이크
![pngtree-stack-of-pancakes-with-syrup-and-blackberries-picture-image_2639071](https://github.com/Ohleesang/TIL/assets/148442711/ad1724ea-c516-4a17-99f3-d31fa3210eb3)

>펜케이크를 예를 들자면, 먼저 만든건 제일 밑에, 그리고 최근에 쌓은 펜케이크를 먼저 먹는다. 

- 선입선출 인 Que(큐) 와 다르게 후입선출(Last In First Out) 특성을 가지고 있다.
- 흔히 메인 메모리, 프로그램에서 함수가 호출될때 이 구조가 사용된다.

![stack drawio2](https://github.com/Ohleesang/TIL/assets/148442711/0e023a96-8d31-42e1-8d97-33d5e9ed5567)
> 대략적인 Stack(스택)의 구조

* push() : 스택의 맨 위 요소 추가
* pop() : 스택의 맨 위 요소 제거

## Kotlin에서 Stack(스택)
kotlin 에서 스택을 구현하려면 기존에 쓰였던 java에서 이용한 Stack 클래스를 이용하거나 
MutableList를 사용하여 구현 가능하다.

```kotlin
import java.util.Stack 
//java.util 를 import!

fun main(){
    val stack = Stack<Int>()
}
```
### Stack Class 관련 Method


1. push(element E):E
 * 스택의 맨 위에 요소를 추가
 * 추가된 요소를 반환
```kotlin
stack.add(1) //   [3](Top)
stack.add(2) //   [2]    이런 형태로 값이추가 
stack.add(3) //   [1]     
```

2. pop():E
 * 스택의 맨 위에 요소를 '제거'
 * 제거된 요소를 반환
```kotlin
stack.pop() // top인 '3'을 제거한 후 반환
//   
//    [2](Top)
//    [1]
```

3. peek():E
 * 스택의 맨 위에 요소를 '반환'
 * 값은 제거되지 않음
```kotlin
stack.pop() // top인 '3'을 제거하지않고 반환
//    [3](Top)
//    [2]
//    [1]

```

4. empty():Boolean
 * 스택이 비어 있는가?
 * 비어 있다면 true,아니면 false
```kotlin
if(!stack.empty()) 
    println("스택은 비어있지 않습니다")
    // 값이 false 이므로 문자열 출력됨
```

5. search(element E):Int
 * 스택이 주여진 요소를 찾아서 Index를 반환
 * 맨 위 요소가 1,그 아래 내려갈수록 값이 증가
 * 찾지 못하면 -1를 반환
```kotlin
stack.search(1) // 맨 아래, 3 반환
stack.search(3) // 맨 위, 1 반환
stack.search(8) // 값이 존재x -1 반환
//      [3] (Top)
//      [2]
//      [1]
```

# 결론
알고리즘을 좀 더 파다 보면 시간복잡도를 고려하게 되고, 이로 인해 자료구조에 접근하게 된다. 의외로 구현이 간단하지만, 응용되는 경우가 매우 많아, 적어도 개념은 단단히 익히는게 좋은거같다.
 
