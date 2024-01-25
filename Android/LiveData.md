# LiveData
### Data의 변경을 관찰 할 수 있는 Data Holder 클래스!
Observe와 함께 사용되며, 해당 Data가 변경되면 설정한 Observe의 코드를 실행할 수 있다.
## 특징

### 1. 수명주기 인식:

- LiveData 는 수명 주기를 인식하고 있다. 
- 메모리 누수를 방지 할 수 있다.
  - 연관된 수명주기가 삭제되면 관찰자가 자동으로 제거된다
### 2. 자동 UI 업데이트:

- 관찰자에게 기본 데이터의 변경 사항을 알리고 이러한 업데이트는 자동으로 메인 스레드에 전달되어짐
- 이로인해 데이터 변경에 따라 UI를 업데이트하는 프로세스를 단순화

### 3. ViewModel과 통합:
- Android 아키텍처 구성요소의 ViewModel 클래스와 함께 사용되는 경우가 많다
- 이 조합을 사용하면 구성 변경을 유지하면서 활동 및 프래그먼트와 같은 Android 애플리케이션의 다양한 구성 요소 간의 데이터 공유가 용이해짐!

### 4. 스레드 안전성:
- 데이터 변경 사항이 메인 스레드에서 관찰되도록 보장하여 UI 구성 요소 업데이트에 편리함
- 보통 백그라운드 작업을 수행한다

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

### 결과
![viewModel](https://github.com/Ohleesang/TIL/assets/148442711/138f9757-7236-4dfc-8c34-6f4c02246d65)

# 결론
API 연결을 공부하면서, 데이터 통신에 특화된 개념이라 다시한번 정리하였다. 즉 생명주기를 알고있는 똑똑한 얘라서, 기본적으로 비동기로 지정한 데이터를 관찰하여 만약 변경이되면 이때 메인스레드로 넘어가서 UI를 변경해주면 되는거라 복잡한 로직없이 백앤드와 프론트를 구분지을 수 있게 고마운 친구인거같다.