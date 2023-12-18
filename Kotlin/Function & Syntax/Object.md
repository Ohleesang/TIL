# Object 키워드
### 싱글톤 객체 생성!
> 애플리케이션 전체에서 단 하나의 인스턴스만 존재하는 객체를 생성, 그 이외에도 다양한 용도로 사용된다.
```kotlin
// 데이터를 담기위한 클래스 생성
data class Video(
    val channelTitle: String,
    val title: String,
    val thumbnail: String,
    val description: String
)
...
// 단 하나의 인스턴스만 생성한다. 
object VideoList {
    private val list: MutableList<Video> = mutableListOf()

    init {
        add(
            Video(
                "MrGamerJay",
                "I tried Creepy Minecraft Seeds \uD83D\uDC80",
                "https://i.ytimg.com/vi/WGJLOTDEUqI/hqdefault.jpg",
                "Discord: https://discord.com/invite/mrgamerjay Instagram:https://www.instagram.com/mrgamerjay/ Facebook ..."
            )
        )
        add(
            Video(
                "\"Ryguyrocky\"",
                "I Survived 100 Days as the GRINCH in Minecraft",
                "https://i.ytimg.com/vi/C7iRz37V2yY/hqdefault.jpg",
                "Buy the brand new merch I just released at ryguy.shop !!!!! Want to be in a future video? Join my Discord!"
            )
        )
        ...
    }
    fun add(video: Video) {
        list.add(video)
    }

}

```
> Youtube UI 예제 중, 비디오의 정보를 담는 객체 Video를 담는 리스트들을 단 하나의 인스턴스로 설정하였다.

Data Class 와 싱글톤 객체를 사용함으로써 video 클래스에 관련된 데이터를 일관성있게 정리할수 있게 되었다.
### 이처럼 전역으로 단 하나의 인스턴스를 생성하고싶을때, Object 키워드를 이용하여 싱글톤 객체를 생성 할수 있다!
그 이외에도 다른 기능들도 이용 될 수 있다.

1. 싱글콘 객체(Sigleton Object)
- 클래스 정의 없이 싱글톤 객체를 생성. 애플리케이션 전체에서 단 하나의 인스턴스만 존재하는 객체
- object 키워드를 사용함 동시에 그 클래스의 단일 인스턴스를 생성!
```kotlin
object MySingleton {
    // 싱글톤 객체의 멤버들
}
```
2. 익명 객체(Anonymous Object)
- 익명 객체를 생성 가능!
 > 클래스를 정의하지않고 객체를 생성하는 방법..
- 인터페이스 구현이나 로컬 범위에서의 객체 정의에 활용!
 > 특정 상황에서 필요한 객체를 편리하게 생성...!!
```kotlin
val myObject = object {
    val property1: Int = 42
    val property2: String = "Hello, Kotlin!"
}
```
3. 인터페이스 구현
- 인터페이스를 구현하는 익명 클래스를 생성 할수 있다.
```kotlin
interface MyInterface {
    fun myFunction()
}

val myInterfaceImplementation = object : MyInterface {
    override fun myFunction() {
        // 인터페이스 메서드의 구현
    }
}

```
4. [Companion Object](https://github.com/Ohleesang/TIL/blob/main/Kotlin/Function%20%26%20Syntax/Compaion%20object(%EB%8F%99%EB%B0%98%20%EA%B0%9D%EC%B2%B4).md)
- 클래스의 인스턴스 없이 해당 클래스에 속한 맴버들을 호출할 수 있다.

```kotlin
class MyClass {
    companion object {
        fun myFunction() {
            // 클래스의 인스턴스 없이 호출할 수 있는 함수
        }
    }
}
//이때 인스턴스 생성없이 myFunction 호출이 가능하다!
```

## 싱글톤 객체를 사용 하는 이유
1. 자원 공유
    - 하나의 인스턴스만 존재
    - 즉, 자원의 중복 사용을 피하고 데이터의 일관성을 유지할 수 있게해줌
2. 공통된 로직의 중앙 집중화
    - 공통으로 사용되는 로직이나 상태를 하나의 객채로 집중 할 수 있다.
    - 이로인해, 코드 유지보수성이 향상되어 중복코드를 방지 할 수 있다.
3. 전역 상태 관리
    - 애플리케이션 전역에서 공유되어야하는 상태를 관리 할때 유용하다.
4. 리소스 공유
    - 한번 로딩된 리소스(파일,네트워크 연결,DB 연결 등)를 여러곳에 공유하여 사용할때 
5. 설정 관리
    - 애플리케이션의 설정 정보를 담아, 일관된 설정을 사용가능
6. 객체의 단일성 보장
    - 예기치 못한 다중 인스턴스 생성을 방지.

싱글톤 객체를 사용함으로써 코드의 모듈성과 유연성을 유지하면서도 특정한 상황에서의 데이터 공유와 중앙 관리를 효과적으로 처리할 수 있다.


# 결론
저번에 공부했던 compaion object들은 객체들 간의 공유할 때 쓰였지만, 이번 object 키워드는 전역 상태 관리할때 더 유용한 스킬이라고 생각이든다. 
> 즉 예제처럼 비디오의 소스들을 저장하고 관리 해야하므로, 오히려 compaion object은 애매해지고, 싱글톤 객체를 하나 만들어서 리스트로 저장하는게 더 효율적으로 보인다. 

즉,객체들 간의 정적인 값이나 메소드를 공유할때는 compaion object를,전역적으로 공유해야할 경우에는 싱글톤 객체를 이용하면 될것같다.