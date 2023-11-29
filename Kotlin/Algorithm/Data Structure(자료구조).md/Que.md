# Que(큐)
### 선입 선출(First In First Out) 특성을 가지는 자료구조

> 대기열, 표를 사기위해 줄을 서는 사람들
![wait que](https://github.com/Ohleesang/TIL/assets/148442711/22b7271e-1c13-4266-8bd7-5d30e08ca143)
> 표를 사기위해 기다리는 사람들, 일찍 서는 사람이 일찍 표를 구입할 수 있다.

* 선입선출(First In First Out), 먼저 들어가면 먼저 나간다.
* 어떠한 작업(데이터)를 순서대로 실행(사용)하기위해 "대기" 시킬떄 이용
* 네트워크에서, 자료를 일시적으로 저장할때 사용


![kotlin-queue-data-structure](https://github.com/Ohleesang/TIL/assets/148442711/ce88285c-2100-4494-a42d-67543d8423e5)

> 대략적인 Que 구조
* front : Que 의 앞부분
* Back : Que의 뒷부분
* enqueue(insert) : 큐의 값 넣기
* dequeue(remove) : 큐의 값 뺴기


## Kotlin에서 Que(큐)
kotlin 에서 Que(큐)를 다루기 위해 'Queue' 인터페이스 와 'LinkedList' 클래스 를 이용한다.

```kotlin
import java.util.LinkedList
import java.util.Queue
//java.util 에서 LinkedList와 Queue 클래스를 import !


fun main(){
    val queue : Queue<T> = LinkedList()
}
```
### Queue Class 관련 Method

1. add(element E):Boolean
 * 큐에 지정된 요소를 추가
 * 요소가 추가되면 true, 공간이 부족해 추가할 수 없으면 false
```kotlin
queue.add(1)
queue.add(2)//     <rear>[3][2][1]<front>
queue.add(3)     
```
 * cf) offer(elemnet) :Boolean
    * add 와 유사하지만, 여유 공간이 없는경우 예외발생 하지않고, false 를 반환한다.

2. remove() :E
 * 스택의 맨 앞에 요소를  '제거'
 * 큐가 비어있을 경우 'NoSuchElementException' 발생!
```kotlin
queue.remove()
//   <rear>[3][2][1]<front>
//   <rear>[3][2]<front> ..dequeue!
```
 * cf) poll() :E?
    * remove()랑 유사하지만 비어있으면 
    'null'를 반환


3. element() :E
 * 큐의 맨 앞에 있는 요소를 '반환'
 * 큐가 비어있을 경우 'NoSuchElementException' 발생!
```kotlin
queue.pop() // top인 '3'을 제거하지않고 반환
//   <rear>[3][2][1]<front>

```
 * cf) peek() : E?
    * element()랑 유사하지만 비어있으면 
    'null'를 반환

## 특수한 형태의 큐
1. 원형 큐(Ring Buffer)
- 큐를 위해 배열을 생성할때 배열의 앞부분 에 뒷부분을 연결하여 사용

2. 우선순위 큐(Heap)
- 원소들에게 우선순위를 매겨서 넣을때 순서와 상관없이 뺄 때에는 우선순위가 높은 원소부터 빼는 것.

3. 데크(Deque;Double Ended Queue)
- 일반적인 큐와 다르게 front ,rear에 둘다 삽입/인출이 가능하다. 스택과 큐의 성질을 가지고 있는 특수한 자료형태



# 결론

기존에 스택을 알아보고 이번에는 큐를 알아보았다. 비록 간단해 보여도 특수한 형태의 큐처럼 응용도 되고 알고리즘에 기반되는 개념이기에 꼭 익히고 가는게 좋을거같다.