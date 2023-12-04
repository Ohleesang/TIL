# 더블 콜론( :: )
### 특정 문법적인 용도로 사용되는 기호! 
```kotlin
startActivity(Intent(this,IntroActivity::class.java))
// Intent 객체를 생성하여 
// IntroActivity에 값을 전달하는 코드

```
>> 이떄, :: 는 도대체 뭘까...? 

더블 콜른( :: )은 프로그래밍에서 특정 문법적인 용도로 사용되는 기호 중 하나이다.
각 언어에따라 다양한 의미로 해석 될 수 있다.


## Kotlin 에서 더블콜론( :: )
### 정적인 참조(Reference) 할 때 사용!
>>정적인 참조. 즉, 클래스나 프로퍼티,함수 등(컴파일 할때...) 을 '명시적' 으로 참조할때 쓰인다.
```kotlin

class MyClass {
    var myProperty :Int = 33

    fun myFunction(x : Int) = x%2 !=1    
}

fun main(){
    var myClass = MyClass::class
    // 클래스 참조
    
    var property = MyClass::myProperty
    // 프로퍼티 참조
    
    println(MyClass::myFunction)
    // 메소드 참조
}

```

# 결론
사전 강의때도 나오고 이번 4주차 강의때도 더블 콜론이 나와서 한번 짚고 가야겠다고 생각했다. 막상 어려운 개념은 아니고, 그냥 명시적으로 참조할때 쓰이는 것이다. 