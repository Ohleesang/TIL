# 원형 큐(Circular Queue)
## 순환하는 자료구조?!
> 기존 큐(Queue) 경우의 단점을 보완한 자료 구조! 마치 고리같이 보여 Ring buffer라고도 한다.

![1_Y8ROjldO8IU8__iZ4y6cvw](https://github.com/Ohleesang/TIL/assets/148442711/3367d3af-37b0-4825-acc9-f32911e30aca)

> 큐의 끝부분(Rear)과 첫번째 부분(Front)을 붙어놓다보니 고리형태가 되어있다.

* 큐의 단점을 보완한 자료구조
* 원형적인 구조로 인해 크기에 제한이 없다!

## 큐(Queue) 의 단점

### 보통 배열 구조로 구현하기 떄문에 생기는 결과들

1. 공간 낭비
    - 기존 큐 에서는 dequeue(remove)를 하면, 앞에 공간이 비어있어서, 이 공간을 활용할 수 가 없다. 
    - 이로인해 Front의 index값을 증가시켜서 처리 한다
```kotlin
[1][2][3]....[123]  // frontIndex = 0
// dequeue! dequeue!
[ ][ ][3]....[123]  // frontIndex = 2
// 앞부분 인자가 낭비된다...
```
2. 큐의 크기제한
    - 배열의 경우 불변이므로,크기가 제한 되어있어 초과된다면 새로운 요소를 넣을수없다.(enqueue)

### 잠깐!
그렇다면 kotlin에서 가변형 자료인 muteableList 형태를 사용하면 되지않는가?
> 물론 가변형을 이용하면 동적으로 데이터를 처리할 수 있다. 
>하지만, 연산(dequeue,enqueue) 을 할때, 모든 리스트 데이터를 앞으로 이동시켜야하는 단점이 발생한다..

```kotlin
[1][2][3]....[123]  
// dequeue! dequeue!
[ ][ ]![3]....[123]!
  
[3]....[123] 
//이 구조의 경우 총 120번의 연산을 실행 시켜야 한다..
```

### 이러한 단점들을 보완한 것이 바로 "원형 큐(Circular Queue)이다!"

## 원형 큐(Cicular Queue) 특징

### 기존 큐의 끝(Rear)부분과 큐의 처음(Fount) 부분을 연결하는 형태!
> 큐가 고리 형태로 이루어져 있다!

![cq2](https://github.com/Ohleesang/TIL/assets/148442711/73c40c68-262f-4629-b8fb-305a6ed6061f)

1. 메모리 공간의 효율성 및 큐 크기 제한 해결
    - 연산 실행시, 만약 크기가 초과 될경우, 데이터 삭제 필요없다.
        > 배열의 첫부분 으로 가면 되므로 메모리 공간이 효율적이다. 
        ```kotlin
        ![4]~~[1][2][3][4]~~[1][2][3]....
                  f     r
        ///enqueue[A]! 
        
        ![4]~~[A][2][3][4]~~[A][2][3]....
               r  f    
        // 그냥 r의 인덱스값을 다시 
        // 처음으로 지정하면 된다
        ```
2. Front 와 Rear 의 이동
    - 데이터의 enequeue,dequeue 작업이 이루어질때, 원형적으로 이동한다.
    > 마치 각도 같다!(0도는 360도랑 같다라는 이치)
    ```kotlin
    front = (front/i)% n
    //n은 배열의 크기 ,i는 인덱스 값
    //
    ```

3. FIFO 구조
    - 기존 queue 에서 파생된개념이라, 기본적으로 선입선출(First In First Out) 구조를 가지고 있다.


## Kotlin 에서 원형 큐(Circular Queue) 구현

기존 큐 처럼 배열과 인덱스 조작을 사용하여 원형적인 동작을 구현하면 된다.

```kotlin
class CircularQueue( maxSize : Int){
    val queue = IntArray(maxSize)
    var front = 0
    var rear = -1
    var size = 0
    var maxSize = maxSize


    fun enqueue(i:Int){
        rear = (rear+1) % maxSize
        //만약 사이즈가 넘어가면 다시 배열 첫부분으로
        queue[rear] = i
        size++
    }

    fun dequeue():Int{
        val i = queue[front]
        front = (front+1) % maxSize
        //만약 사이즈가 넘어가면 다시 배열 첫부분으로

        size--
        return i
    }

    fun peek():Int{
        return queue[front]
    }
    fun printQueue(){
        for(i in queue){
            print("[${i}]")
        }
        println()
    }
}
fun main(){
    val circleQue = CircularQueue(3)// 100의 크기인 원형 큐를 생성
    circleQue.enqueue(1)
    circleQue.printQueue()
    circleQue.enqueue(2)
    circleQue.printQueue()
    circleQue.enqueue(3)
    circleQue.printQueue()
    circleQue.enqueue(4)
    circleQue.printQueue()
    circleQue.enqueue(5)
    circleQue.printQueue()
}
/////////////////////
//[1][0][0]
//[1][2][0]
//[1][2][3]
//[4][2][3] 초과되니까 첫번째인자로!
//[4][5][3] 계속 채워준다
/////////////////////

```

# 결론
기존 큐와 스택을 공부하고, 이번에 큐의 응용인 원형 큐를 공부하였다. 그냥 front와 rear을 연결 하였을뿐인데 공간 효율이 뛰어나는걸 보아 나중에 큐를 이용하여 알고리즘을 구현할때 크게 도움이 될꺼같다.