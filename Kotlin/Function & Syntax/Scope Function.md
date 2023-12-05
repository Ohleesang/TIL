# Scope Functions
### 자기 자신의 객체를 전달해서 효율적인 처리!
> 객체를 사용할때 임시로 Scope를 만들어서 편리하게 코드를 작성 할수있는 기법



## Scope Function 의 종류

|  | Scope에서 접근 방식 this | Scope에서 접근 방식 it |
| :-- | :-- | :-- |
| 블록 '수행 결과'를 반환 | run,with | let |
| '객체 자신'을 반환 | apply | also |

### 잠깐!
#### this 와 it 의 차이점은 뭔가요??
* this : 현재 객체를 가르키는 키워드
    >주로 클래스나 확장 함수에 맴버 변수나 함수에 접근할때 사용
* it  : 람다 식 내에, 람다의 인자를 가르키는 키워드
    > 주로 람다 식 내에서 사용,람다의 인자를 가르침

Scope라는 공간을 임시로 활성화하여 처리
즉 객체의 초기화와 조작을 명확하게 할 수 있으므로, 코드의 가독성이 향상된다.


### cf) 람다식??
[람다식이란...](https://github.com/Ohleesang/TIL/blob/main/Kotlin/Function%20%26%20Syntax/lamdda%20expression(%EB%9E%8C%EB%8B%A4%20%EC%8B%9D).md)

* let Function
    - { } 안에 it으로 자신의 객체를 전달하고 수행된 결과를 반환
```kotlin
var strNum = "10"
var result = strNum?.let{
    //?. : 만약 널이 아닐시 시행
    Integer.parseInt(it)
}

println(result!!+1) //11 출력

```
* with Function
    - { } 안에 this로 자신의 객체를 전달하고 코드를 수행
    - null값이 아닐때 사용하는게 좋다!
```kotlin
var alphabets ="abcd"

with(alphabets){
//  var result = this.subSequence(0,2)
    var result =subSequence(0,2)
    println(result)//abc 출력
}

```

* also Function
    - { } 안에 it으로 자신의 객체를 전달하고 객체를 반환한다.
```kotlin
fun main(){
    var student = Student("참새",10)

    var result = student?.also{
        it.age = 50
    }
    result?.displayInfo() // 출력 A 
    student.displayInfo() // 출력 A
    //두개 다 같은값이 들어가있는 객체다!
}
```

* apply Function
    - { } 안에 this로 자신의 객체를 전달하고 객체를 반환
    - 객체의 상태를 변화시키고 바로 저장하고 싶을때 사용!
```kotlin
fun main(){
    var student = Student("참새",10)

    var result = student?.apply{
        student.age = 50
    }
    result?.displayInfo() // 출력 A 
    student.displayInfo() // 출력 A
    //두개 다 같은값이 들어가있는 객체다!
}
```


* run Function
    - { } 안에 this로 자신의 객체를 전달하고 코드 수행 
    - 객체가 없을 경우(Null)도 사용이 가능!!
    - 주로 객체 초기화나 연속적인 작업을 진행 할때 사용!
```kotlin
var totalPrice = run{
    var computer = 10000
    var mouse = 5000

    computer+mouse // 값 return
}
println(totalPrice)// 15000 출력
```
```kotlin
fun main() {
    var student = Student("참새", 10)
    student?.run {
        displayInfo() //출력 A
    }
}
```
이때, with 과 다르게 run 은 null체크를 수행 할 수 있으므로 더욱 안전하게 사용이 가능하다!

### 수신객체(this)와 람다함수(it) 
* 수신객체 자체를 람다의 '수신객체'로 전달
```kotlin
public inline fun <T, R> T.run(block: T.() -> R): R
//T : 수신객체 ,R : 결과값
//T.run : 확장함수
//block : 람다함수 (T에대해 '어떤작업을 수행'후 R값을 반환)
```
* 수신객체를 람다의 '파라미터'로 전달
```kotlin
public inline fun <T, R> T.let(block: (T) -> R): R
//T : 수신객체 ,R : 결과값
//block : 람다함수 (T를 '인자값'으로받아 R을 반환)

```

### Scope Function 중복 사용
> 모든 수신객체를 it으로 활용하면 누가 누군지 모르기떄문에 변수를 명시해준다.
```kotlin
// 잘못된 예시
Person().also {
	it.name = "한석봉"
	it.age = 40
  val child = Person().also {
	  it.name = "홍길동" // 누구의 it인지 모른다!
    it.age = 10 // 누구의 it인지 모른다!
  }
  it.child = child
}

// 수정한 예시
Person().also {
	it.name = "한석봉"
	it.age = 40
  val child = Person().also { c ->
	  c.name = "홍길동"
    c.age = 10
  }
  it.child = child
}

```

# 결론

코틀린을 공부하면서 얼마나 Null 안정성과 코드의 간결성을 고려한지를 알게된 개념중 하나이다. 단순히 간편하다기보단, 효율적으로 짤수있으므로 숙지 해놓은게 좋을꺼같다.









