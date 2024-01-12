# Model - View - ViewModel
### 모델,뷰,뷰모델 이라는 세 구성 요소를 가진 디자인패턴!
![mvvm](https://github.com/Ohleesang/TIL/assets/148442711/faa3f36d-1d01-420c-b758-3f82ba9c702a)


기존의 경우, 코드를 작성할때 한 액티비티에서,UI 와 데이터 처리를 동시에 하였다.

데이터를 처리하고 UI를 업데이트하고 이런 과정을 한 코드내에 해야하니 기능이나 변수가 늘면 늘수록,
코드가 복잡해져 가독성이 떨어지고 유지보수가 힘들어진다. 
### 이러한 불편한 점을 고안해서, 데이터 처리와 UI부분을 구분지어놓자는 개념이 바로 MVVM 디자인패턴이다.

## MVC vs MVVM

### @ MVC : Model - View - Controller
![mvc](https://github.com/Ohleesang/TIL/assets/148442711/24150410-abbd-484c-8d36-b934299ee809)

- Model : 주고 받을 수 있는 객체,데이터
- View : 출력 부분(UI),데이터를 표시하거나 사용자에게 정보를 제공
    - xml에서 디자인및 UI 가 정의
    - 조작하는 부분은 액티비티에 처리
- Controller : 처리하는 부분 , 사용자 입력을 처리하여 모델이나 뷰를 업데이트
    - 이벤트 처리(콜백 함수)
    - 데이터 업데이트

### 출력부분과 처리하는부분 그리고 데이터 관리가 한 액티비티 내로 처리되어 코드가 직관적이다.

### @ MVVM : Model - View - ViewModel
![mvvm2](https://github.com/Ohleesang/TIL/assets/148442711/b47c080c-87e6-4cb5-a238-a14b33d50fc3)


- Model : 주고 받을 수 있는 객체,데이터
- View : 출력 부분(UI), 사용자에게 정보를 표시하거나 입력을 받음.
- ViewModel : 뷰와 모델의 중간 매개체
    - 뷰에 표시할 데이터를 관리
    - 사용자 입력에 대한 이벤트 처리

### mvc 와 달리 뷰 와 모델이 완벽하게 분리 되어져 있어서 코드의 유지보수성과 테스트 용이성이 높다.

### 요약

mvc 같은경우, controller가 뷰와 모델 둘다 관여하고 일을 수행하는 느낌이고,

mvvm 같은 경우, viewModel 이 뷰와 모델의 징검다리 같은 역할을 하는 느낌이다.


## MVVM 구성요소

### Model
- 애플리케이션의 비즈니스 로직과 데이터를 담당
- 데이터베이스와 상호 작용
- 외부 소스로부터 데이터를 가져옴
### View
- 사용자 인터페이스(UI) , 사용자에게 데이터를 표시하고 사용자 입력을 처리
- 뷰와 뷰모델 간의 데이터 바인딩을 사용
    - 뷰의 표시와 뷰모델의 데이터가 자동으로 동기화
    - 뷰는 데이터의 상태 변화에 대해 즉각적으로 반응(옵저버 디자인 패턴)
- 뷰모델로부터 전달받은 데이터를 기반으로 UI를 업데이트

### ViewModel
- 모델(Model)과 뷰(View) 사이에서 중간 매개체 역할  
- UI에 필요한 데이터를 적절하게 가공하고 관리
- 데이터 바인딩을 통한 뷰와 양방향 통신
    - 뷰모델의 상태 변경이 자동으로 뷰에 반영
    - 사용자 입력이 뷰모델로 전달
- UI 상태 관리
    - 화면 회전 ,다른 사용자 인터랙션에 의해 손실되기 쉬운 데이터나 상태를 저장하고 관리

### View <-> ViewModel
- 이벤트 처리
    - 이벤트가 생기면 뷰모델에게 처리해줘 라고 알려줘야한다.
    > 버튼 클릭이나 텍스트 입력
- 뷰모델에 데이터 갱신 요청
    - 이벤트 처리 이후, 필요에 따라 데이터 갱신을 요청한다.
    - 뷰모델은 데이터 갱신 요청 을받고, 로직을 수행하고 데이터를 업데이트 한다.
- 데이터 관찰
    - livedata 를 관찰하여 데이터의 변화를 감지함
    - 데이터가 변경되면 자동으로 뷰에 업데이트 된다.
- UI 업데이트
    - 데이터의 변경이 감지가 되면, 뷰는 해당 데이터로 UI를 업데이트함
    > 텍스트 뷰의 내용,배경색 등


### ViewModel <-> Model
- 데이터 관리
    - 모델로부터 필요한 데이터를 가지고옴
    - 가져온 데이터를 가공하여 적절한 형태로 변환
- 비즈니스 로직 처리
    - 사용자의 요청에 대한 처리,데이터의 유효성 검증, 계산같은 작업
    - 데이터의 상태를 관리하거나,상호 작용을 다룸
- 뷰와 인터렉션 관리
    - 뷰에서 받은 사용자 입력에 대한 응답을 모델에 업데이트
- 라이프사이클 관리
    - 뷰의 라이프사이클에 맞춰 동작
    - 뷰의 상태에 맞춰서 데이터를 관리


## 예제(버튼 클릭시, 해당 배경 랜덤으로 변경해보기)
### 1. MyViewModel Class 구현
- ViewModel 클래스를 상속받아 뷰모델 클래스를 구현한다.
- 해당 클래스에는 배경색을 랜덤으로 값을넣는  **로직**을 구성하였다.
```kotlin
class MyViewModel : ViewModel() {

    private val _backgroundColor = MutableLiveData<Int>()
    val backgroundColor : LiveData<Int> get() = _backgroundColor

    fun updateToBackground(){
        _backgroundColor.value  = randomColor()
    }

    private fun randomColor():Int{
        val r = (0..255).random()
        val g = (0..255).random()
        val b = (0..255).random()
        return Color.rgb(r,g,b)
    }
}
```
#### 프로퍼티
------------------------
```kotlin 
private val _backgroundColor = MutableLiveData<Int>()
```
- MutableLiveData을 사용 하여 데이터를 변경 가능 하도록 설정

- 뷰모델 내에서 처리하는 변수(읽기,쓰기 가능) 
------------------------
```kotlin 
val backgroundColor: LiveData<Int> get() = _backgroundColor
```
- LiveData를 통해 외부에서 변경되지 않도록 설정
- #### View 에서 받는 변수(읽기만 가능)
------------------------
#### 메소드
------------------------
```kotlin 
fun updateToBackground()
```
- ### 색상값을 liveData 형태로 저장하는 로직을 구성
------------------------
### 2. MainActivity 작성
```kotlin
class MainActivity : AppCompatActivity() {
    //A. viewModel 선언
    private val viewModel by lazy { ViewModelProvider(this)
    [UserViewModel::class.java] }

    ...


     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //B. 옵저버 등록
        viewModel.backgroundColor.observe(this){backgroundColor ->
            updateBackgroundColor(backgroundColor)
        }

        //C. 이벤트 발생시 콜백함수 등록(버튼 클릭시 색깔이 변경)
        binding.updateButton.setOnClickListener {
            viewModel.updateToBackground()
        }

        

    }

    private fun updateBackgroundColor(newColor:Int){
        binding.root.setBackgroundColor(newColor)
    }
}
```
### 프로퍼티
------------------------
```kotlin
private val viewModel by lazy { ViewModelProvider(this)
    [myViewModel::class.java] }
```
- #### ViewModel 초기화(인스턴스 생성)
------------------------



### 메소드
------------------------
```kotlin
binding.updateButton.setOnClickListener {
            viewModel.updateToBackground()
        }
```
- 버튼 클릭시 콜백함수를 정의
- ViewModel의 updateToBackground 메서드를 호출
- #### 이로인해 backGroundColor liveData가 업데이트 되고, 이에 따라 옵저버가 호출


------------------------
```kotlin
viewModel.backgroundColor.observe(this){backgroundColor ->
            updateBackgroundColor(backgroundColor)
        }
```
- #### 옵저버 등록
    - 데이터가 변경될 때 마다 호출됨
    - 뷰모델에 정의된 backgroundColor : liveData 를 받아서 UI를 업데이트하는 함수로 넘긴다.
------------------------
```kotlin
private fun updateBackgroundColor(newColor:Int){
        binding.root.setBackgroundColor(newColor)
    }
```
- UI 배경색을 업데이트하는 함수
- #### viewModel에 데이터를 받아 배경색을 변경한다.
------------------------

#### MainActivity 흐름

버튼 클릭 -> 뷰모델 내에 메소드 호출 -> 데이터 변경 감지 -> 옵저버 호출 -> 데이터를받아 UI 업데이트

### 결과
![viewModel](https://github.com/Ohleesang/TIL/assets/148442711/138f9757-7236-4dfc-8c34-6f4c02246d65)


# 결론
기존의 방식은 한 액티비티내에서 처리하는 MVC 방식이었고, MVVM 방식은 필드에서도 많이 쓰이기 때문에 보다 중요한개념이라 생각해서,TIL로 작성하였다. 요약하자면, MVC는 Controllor가 뷰와 모델 을 둘다 관여하여 처리하는느낌이고, MVVM은 뷰와 모델 사이의 징검다리 역할을 해주는 느낌이다. 
이렇게 한다면, 뷰에서는 UI에 대한 처리(UI를 보여주거나,이벤트처리시 뷰모델에게 알려주기)만 작성하면되고,뷰모델에서는 데이터 관리 및 처리를 해주면되어서 모듈화가 되어 오히려 앱을 개발할때 유지보수가 더 쉬워진다.그리고 무엇보다 옵저버로 데이터변동이 될때 자동으로 화면을 재구성 해준다는게 너무나도 큰 장점이라고 생각된다.