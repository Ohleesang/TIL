# SmoothBottomBar
### 외부 라이브러리 이용하여 더 멋진 BottomNavigator 만들기!

![68747470733a2f2f63646e2e6472696262626c652e636f6d2f75736572732f313031353139312f73637265656e73686f74732f363235313738342f736e6170702d2d2d616e696d6174696f6e2e676966](https://github.com/Ohleesang/TIL/assets/148442711/13b0c69b-cfa2-4973-b484-726a3b450f4f)

이번에 바텀네비게이션을 구성하는데 더 이쁘게 꾸미고싶다. 그래서 기존 사람들이 작성한 라이브러리를 참고하여 구성하는 방법을 소개하고자한다.

### 라이브러리 설명 링크
[Github Link](https://github.com/ibrahimsn98/SmoothBottomBar)

# 구현
<img width="400" alt="스크린샷 2024-02-15 오후 7 37 20" src="https://github.com/Ohleesang/TIL/assets/148442711/3d6bd111-dcb8-4676-98ca-b1c72de0d973">

앱 구성은 기본 BottomNavigation Views Activity 기반으로 작성했음을 알린다.

(Material Design 가이드라인에 따른 BottomNavigationBar

## 종속성 추가 
- app/build.gradle.kts
    ```kotlin
    dependencies {
        ...
    // smoothBottomBar
    implementation("com.github.ibrahimsn98:SmoothBottomBar:1.7.9")
    }
    ```
- settings.gradle.kts
    ```kotlin
    dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
    ```
### Maven?
- 자바 프로젝트의 빌드 도구 중 하나
  - 빌드,패키징,문서화,테스팅과 배포등을 쉽게 할수있게해줌
  - 프로젝트에 필요한 라이브러리와 플러그인을 동적으로 다운로드

-> Gradle 프로젝트가 Maven 저장소에 있는 라이브러리를 사용 할 수 있게 하기위한 설정!

## BottomNavigator -> SmoothBottomBar by XML
- android:id,android,app:menu 값은 유지하되, 태그를 변경한다.
```xml
<me.ibrahimsn.lib.SmoothBottomBar
        android:id="@+id/nav_view"
        android:layout_height="60dp"
        app:cornerRadius="40dp"
        app:backgroundColor="@color/teal"
        app:indicatorRadius="40dp"
        app:textSize="15sp"
        app:itemFontFamily ="@font/en_nuly"
        app:iconSize="25dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:menu="@menu/bottom_nav_menu" />
```
### 속성
| 속성 | 설명 |
|------|------|
| app:menu | 메뉴 아이템을 정의하는 XML 리소스를 참조 |
| app:backgroundColor | 바탕색을 설정 |
| app:indicatorColor | 선택된 메뉴 아이템에 표시되는 인디케이터의 색|
| app:indicatorRadius | 인디케이터의 둥근 모서리의 반지름|
| app:cornerRadius | 바의 모서리 둥근 정도|
| app:corners | 바의 모서리 둥근 정도(deprecated) |
| app:sideMargins | 바의 양 옆 마진 |
| app:itemPadding | 메뉴 아이템의 패딩|
| app:textColor | 메뉴 아이템의 텍스트 색|
| app:badgeColor | 메뉴 아이템의 배지 색|
| app:itemFontFamily | 메뉴 아이템의 글꼴|
| app:textSize | 메뉴 아이템의 텍스트 크기|
| app:iconSize | 메뉴 아이템의 아이콘 크기|
| app:iconTint | 메뉴 아이템의 아이콘 색|
| app:iconTintActive | 활성화된 메뉴 아이템의 아이콘 색|
| app:activeItem | 처음에 활성화된 메뉴 아이템 |
| app:duration | 인디케이터 애니메이션의 지속 시간|


## BottomNavigator -> SmoothBottomBar by MainActivity


### 1. nav Controller 연결하기(기존 정의한 fragment)
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ...

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarMainActivty)

        navController = findNavController(R.id.main_frame_layout)

        initView()
    }
```
### 2. 네비게이션 컨트롤러가 바텀 네비게이션 뷰의 메뉴 아이템 선택을 자동으로 처리하기위해 설정
- BottomNavigationBar 를 초기화
- BottomNavigationBar 와 navController를 연결
```kotlin
override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.bottom_nav_menu, menu)//메뉴 리소스를 인플레이트
        binding.navView.setupWithNavController(menu!!, navController)
        return true
    }
```

## 결과

![ezgif-7-9b30516eab](https://github.com/Ohleesang/TIL/assets/148442711/38e20688-afd2-4fbf-9407-c4559d5b22ac)
