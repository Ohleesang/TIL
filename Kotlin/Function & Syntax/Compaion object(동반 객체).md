# Companion Object(동반 객체)
### 클래스 내부에 정적인 맴버를 가지고싶을때!
```kotlin
class MyClass {
    companion object { 
        const val staticVar = "I am a static variable"
        
        fun staticFunction() {
            println("I am a static function")
        }
    }
}

```
>> 이때, 프로그램이 실행되면 메모리에 항상 올라오는것 즉 정적인 변수나 메소드를 선언..

Compaion object(동반 객체)란, 클래스 내부에 정의된 객체로, 해당 클래스의 인스턴스와 관련된 정적인 맴버를 가지는데 사용.
 -  클래스 내부에서 하나만 존재.
 - 해당 클래스의 인스턴스를 생성하지않고도 멤버를 정의할수있다.

 ```kotlin
 class MyClass
 
 fum main(){
    println(MyClass.staticVar) // 인스턴스를 따로생성하지않아도 접근이 가능하다!
    MyClass.staticFunction()
 }
 ```

 ### 잠깐! 그렇다면 C에서의 전역변수 같은 개념인가요??
 #### 아니다. 
 물론, 정적 맴버에 해당하는 개념이라 할수있지만, kotlin 에서는 클래스 외부에 직접적인 전역볌수나 함수 선언하는 문법은 없다... 대신에 패키지수준 최상위 수준의 함수와 변수를 선언할수 있다. 

 ```kotlin
 // 최상위 수준 변수
val globalVar = "I am a global variable"

// 최상위 수준 함수
fun globalFunction() {
    println("I am a global function")
}

fun main() {
    // 최상위 수준 변수와 함수에 직접 접근
    println(globalVar)
    globalFunction()
}

 ```

 ### 왜 이런 문법을 쓰는걸까?

>> 코드를 짜다보면 공통적인 상수나 메소드가 있다면,그냥 전역으로 설정하면 더 편하지 않은가...흠

#### 결론부터 말하자면.
## Kotlin은 객체지향언어 이기 때문이다. 
즉 데이터를 캡슐화하고 객체 간의 상호작용을 통해 프로그램을 구성하는것을 중요시 한다.
즉 전역변수를 사용하면, 규모가 커지면 커질수록 캡슐화나 유지보수가 더 복잡해진다. 이로인해 Kotlin은 최대한 전역변수를 피하는것을 권장하여 
### 최소한 '객체들' 간의 상태를 공유할때 Compation object 를 이용한다.  

# 결론
왜 항상 전역변수 설정하는 방식이 없다 생각했는데, Kotlin 은 객체에 ,객체에 의한 ,객체를 위한 프로그래밍 언어라고 생각이 든다. (심지어 이 개념도 객체들간의 상태를 공유한거니까.)
즉 객체들간의 상태를 공유하고싶을때 이 문법을 쓰면, 더욱더 스킬있는 코드를 작성할수 있을꺼같다.