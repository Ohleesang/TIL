# lateInit & by lazy
## 지연 초기화!
프로그램에서 변수나 속성을 선언할때,
### 해당 변수나 속성을 처음으로 사용하기 전까지 값을 초기화하지않는 것
> 지연 초기화 개념은 불필요한 객체 초기화를 방지하기 위해 고안되었다.이때 쓰이는 키워드는 크게 2가지 lateInit,by lazy 가 있다!


## 차이점
||lateInit|by lazy|
|-|-|-|
|초기화 시점|필요한 시점에 직접|처음 사용될때 초기화|
|Nullable|x|o|

### lateInit
```kotlin
lateinit var myProperty: String
```
- lateinit 으로 선언된 프로퍼티는 non-null 타입이어야한다.
  
- 초기화 하지 않은상태에서 프로퍼티에 접근하면 예외 발생!
> UninitializedPropertyAccessException

### 즉, 해당 프로퍼티가 실제로 사용되기전에 반드시 초기화가 수행되어야한다.

### by lazy
```kotlin
val myProperty: String by lazy {
    // 초기화 로직
    "Initialized!"
}
```
- 'lazy'로 선언된 속성은 처음 사용되는 시점에만 초기화된다.
- 스레드에 안정성이 있고,동시에 접근해도 초기화 블록은 한번만 실행되고
해당 값은 공유 된다.
- 자기가 원하는 필요한 시점에 자원을 할당 할수있다.

### 즉, 변수가 처음으로 사용되는 시점에서 초기화가 된다!


# 예제
```kotlin
class SignInActivity : AppCompatActivity() {


    private val id by lazy{ findViewById<EditText>(R.id.editText_id)}
    private val pw by lazy{ findViewById<EditText>(R.id.editText_pw)}

...

 btn_login.setOnClickListener {
            var intent = Intent(this, HomeActivity::class.java)
            var inputId: String?
            var inputPw: String?
            inputId = id.text.toString() // 이때 초기화 실행!
            inputPw = pw.text.toString() // 이때 초기화 실행!

...
 
```
> 사전에 미리 변수를 선언하고,로그인 버튼을 누르면 그값이 초기화된다!

## lateInit 과 by lazy 의 사용
- lateinit : 클래스의 전체 라이프사이클동안 초기화를 늦추고자 할때 사용
- by lazy : 특정한 프로퍼티가 처음 사용될떄 가지 초기화를 미루고자 할때 사용

# 결론
> 이번 개인 과제중에 공통된 변수가 있어서, 그걸 프로퍼티로 저장했는데 막상 앱이 꺼져서 이유를 알아보니까 레이아웃이 선언되기전에 초기화를 해버리기 때문이었다. 그래서 레이아웃을 불러들일때 초기화를 할수 있는 방법으로 지연 초기화 by lazy 를 이용하여 코드를 재작성하였다.

이로 인해, 코드 가독성이 훨씬 좋아졌고, by lazy도 강력하지만, 상황에 따라 lateInit 도 써보는것도 좋을거 같다. 