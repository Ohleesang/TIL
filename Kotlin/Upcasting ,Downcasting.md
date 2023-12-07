# UpCasting? DownCasting?
### 객체를 다른 데이터 타입으로!

> 객체 지향에서 다형성과 관련되어져 있다. ,즉 상속 관계에서 이용되는 개념...!

![upcasting-and-downcasting-in-java](https://github.com/Ohleesang/TIL/assets/148442711/01af74a3-9919-4dd7-bf6f-39d51f17f17e)
> 어린 아이는 부모를 항상 위로 봐야한다(?)...

## 캐스팅 이란?
캐스팅(Casting)이란, 객체를 다른 데이터의 타입으로 변환하는것을 의미.

### 잠깐! 형변환이랑 같은 개념인가요?
#### 비슷하지만 다른개념이다 . 
보통 형변환의 경우는 기본 자료형간의 변환을 의미하고, 캐스팅의 경우는
객체 타입간의 변환을 의미한다.
```kotlin

 // 형변환 
var intStr = "10"
var int = intStr.toInt()

open class Animal
class Dog : Animal()
fun main(){
    val animal : Animal = Dog() // 업캐스팅? 다운 캐스팅?

    //캐스팅
    if(animal is Dog){
        val dog: Dog = animal // 안전한 캐스팅
        println("캐스팅 성공!")
    }

}

```

## Kotlin에서의 캐스팅
캐스팅이란, 객체간의 변환을 의미하고 코틀린에서 자주 쓰이는 캐스팅은 이렇다.
1. Upcasting,Downcasting
    - 상속 관계를 가질때 쓰이는 캐스팅

2. Safe Casing(as?)
    - as? 연산자를 사용하여 안전하게 캐스팅 만약 실패하면 null을 반환
```kotlin

val obj: Any = "Hello"
val str: String? = obj as? String // 이때 String 타입이아니면, null을 반환!

```
3. Smart Casting
    -  타입을 검사한 후, 해당 변수를 자동으로 캐스팅
```kotlin
val obj: Any = "Hello"
if (obj is String) {
    // 여기서는 obj가 자동으로 String으로 캐스팅됨
    println(obj.length)
}

```
4. 컬렉션 타입의 캐스팅
    - 리스트나 배열 등의 컬렉션 타입에서도 다양한 캐스팅이 가능
```kotlin
val anyList: List<Any> = listOf("Hello", 42, 3.14)
val stringList: List<String> = anyList.filterIsInstance<String>()

```

# UpCasting(업캐스팅)
### 객체를 상위 클래스로 변환 하는 캐스팅을 의미.
상속관계에서 다형성을 이용하는 방식이다. 즉, 여러가지 하위 클래스를 "하나의 상위 클래스"로 다룰 수 있어서 코드를 유연성 있게 작성할 수 있다!

```kotlin
open class Animal

class Dog : Animal() {
    fun bark() {
        println("Woof!")
    }
}

class Cat : Animal() {
    fun meow() {
        println("Meow!")
    }
}

fun main() {
    val dog: Dog = Dog()
    val cat: Cat = Cat()

    // 업캐스팅
    val animal1: Animal = dog // Dog를 Animal로 업캐스팅
    val animal2: Animal = cat // Cat을 Animal로 업캐스팅

    // 업캐스팅된 객체 사용
    // 아래 주석 처리된 부분은 업캐스팅된 객체에서는 사용할 수 없는 메서드이므로 에러가 발생!!
    // animal1.bark()
    // animal2.meow()
}
```

# DownCasting(다운 캐스팅)
### 상위클래스 타입으로 업캐스팅된 객체를 다시 하위 클래스 타입으로 변환하는것을 의미.
업캐스팅된 객체를 다시 원래의 타입의 메소드나 속성을 사용할때 사용한다.
> 주로 as,is 연산자를 이용
```kotlin
open class Animal

class Dog : Animal() {
    fun bark() {
        println("Woof!")
    }
}

class Cat : Animal() {
    fun meow() {
        println("Meow!")
    }
}

fun main() {
    val animal: Animal = Dog() // 업캐스팅

    // 다운캐스팅
    if (animal is Dog) {
        val dog: Dog = animal // 안전한 다운캐스팅
        dog.bark()
    } else {
        println("Not a Dog")
    }
}

```

# 결론
캐스팅은 상속의 다형성을 이용하기위한 필수적인 개념이다. 
> 즉, 상속된 부모,자식 클래스를 짠 이후, 공통된 메소드를 이용하거나,컬렉션으로 하나로 묶을때는 업캐스팅을 이용.
> 그리고 업캐스팅된 자식클래스의 메소드를 이용하고싶다하면 다운캐스팅으로 안전하게 변환이후 이용하면 된다. 

이처럼 객체지향언어에서 상속은 필연적 일텐데 오버라이딩이나 오버로딩 처럼 중요한 개념중 하나인거 같다.