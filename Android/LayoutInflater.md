# LayoutInflater
### 안드로이드에서 레이아웃 리소스(XML)를 실제 뷰 객체로 변환하는 역할 하는 클래스
```kotlin
abstract class LayoutInflater
```

#### 동적으로 UI를 다루고싶을때 쓰는 기본적인 클래스!

## Inflation(인플레이션)
일반적으로 메모리에 객체를 생성하는 과정이다. 안드로이드에서는
#### XML에 정의된 레이아웃 파일을 실제 런타임 뷰 객체로 변환하는 과정을 의미한다.
> 앱에서 UI를 설계할때, 레이아웃을 XML파일에 정의하고 앱이 실행되면 XML 파일을 메모리에 로드하고 런타임에 실제 뷰 객체로 변환하는 과정

- 동적으로 UI를 생성
- 레이아웃을 재사용
- 다양한 화면크기와 디바이스 대응
- 코드에서 동적으로 뷰를 생성하여 UI를 제어 할 수 있음

### View binding 에서 Inflation 
기본 레이아웃을 실제 뷰바인딩 클래스로 변환해주고, 이로 인해 체계적으로 뷰를 참조하고 조작할 수 있다.

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
}
```
[기존 onCreate 코드]
```kotlin

private lateinit var binding : ActivityMainBinding
//activity_main.xml 에 대한 자동으로 생성된 클래스

override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
}
```
[View Binding을 사용한 onCreate 코드]

기존의 경우, findViewById() 메서드를 이용하여 Id를 일일히 다 지정해서 처리했지만
### binding으로 지정된 레이아웃들 내에 모든 View 접근가능하다!

## View binding 이랑 LayoutInflater의 차이

- View binding  
  - 정적인 UI 컴포넌트에 대한 참조를 뷰바인딩을 통해 얻어온다.
  - NullSafey,Type 안정성 보장
    > xml 레이아웃에 정의된 뷰 컴포넌트를 자동으로 클래스를 연결해주기때문에

- LayoutInflater 
  - 동적으로 UI를 생성하거나 레이아웃을 변경한다.
  - Activity 나 Fragment에서 레이아웃을 인플레이트하여 UI를 설정함

### 즉, 메인으로 쓸 레이아웃을 LayoutInflater를 이용하여 ViewBinding객체로 인플레이트 한 이후, Binding 객체에서 동적으로 UI를 처리한다.

## 레이아웃의 재사용
기존 xml에서 include 기능을 LayoutInflater와 ViewBinding 을 사용하여 구현이 가능하다.
```xml
<!-- main_layout.xml -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <!-- 동적으로 추가될 영역 -->
    <FrameLayout
        android:id="@+id/dynamic_container"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <!-- 기본적인 내용 -->
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello, ViewBinding!" />

</LinearLayout>

```

```kotlin
// 동적으로 추가할 레이아웃을 인플레이트
val dynamicView = layoutInflater.inflate(R.layout.dynamic_content, null)

// 동적으로 추가할 레이아웃을 main_layout.xml에 포함된 FrameLayout에 추가
val dynamicContainer: FrameLayout = findViewById(R.id.dynamic_container)
dynamicContainer.addView(dynamicView)

```

## 결론
안드로이드 심화 주차를 듣는중에 계속 인플레이터가 뭔지 나오고 설명은 되지않아서 개념이 애매해서 이렇게 공부하면서 어느정도 이해가 되었다. 인플레이터란, xml에서 작성된 UI, 뷰에대한 정보를 실제보여지는 화면에 메모리를 올리는 과정을 의미하며,화면에 올라온 View들을 체계적으로 관리하기위해 View binding과 layoutInflater 를 활용한다. 