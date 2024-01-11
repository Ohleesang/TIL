# 쿠팡이츠 (Clone UI)
### 앱 숙련주차에 배운 RecyclerView,Fragment 활용해보기!

[!프로젝트 링크](https://github.com/Ohleesang/CoupangEatsAppMarkII)

## 사용한 기술

### data
  - InstanceData
    - data class 의 리스트들 을 저장(Menu,Rest)
    - 더미 데이터들을 저장하고 관리하는 오브젝트 클래스
    - 각 데이터의 정보를 액셀 형식(.xlsx)으로 저장하여 읽어들이고,만든 데이터 클래스에 저장하였다.
### navigation
 - 앱의 하단 및 상단 부분에 네비게이션의 아이콘 클릭시 각 해당 프래그먼트를 출력한다.
 - 각 프래그먼트 에 대한 customToolbar를 구현
### fragment
 - homeFragment : 해당 가게들을 보여주는 Fragment
 - searchFragment : 각 메뉴들을 보여주는 Fragment
### RecyclerView
저장된 더미데이터들을 보여주기위해 사용
    
 - homeFragment →  1.  이즈 추천 맛집 부분 2. 골라 먹는 맛집 부분
 - searchFragment → 3. Menu를 화면에 맞게 구성
### RecyclerView.Adapter, ViewHolder
 - 각 해당되는 데이터들을 보여주기위해 커스텀한 레이아웃을 인플레이트후 ViewHolder에 전달
 - ViewHolder에 바인딩을 하고 난 후, 보여지는 더미데이터 값을 입력하여 출력
### layoutManager
 - RecyclerView에 데이터들을 보여주기위한 레이아웃을 지정함

## 시연 영상
<video src=https://github.com/Ohleesang/TIL/assets/148442711/d23dc2b0-65c1-4497-bd82-4b55fd11aea2 width=180></video>


## 트러블 슈팅

네비게이션 아이콘을 클릭 했을때 프래그먼트는 변경되지만 해당 아이콘이 보이지 않는다.

  → 아이콘 이미지를 Vector 소스 를 PNG 파일로 변경해서 생긴 문제

- 네비게이션을 사용할때 기본적으로 Tint 기능이 설정되어있다
- Vector 소스는 자동으로 처리되지만 PNG의 경우는 따로 명시적으로 지정해줘야한다.

### 해결

 Tint를 비활성화하였다.

- 네비게이터 태그 값(.xml)에 틴트를 비활성화
```kotlin
 <com.google.android.material.bottomnavigation.BottomNavigationView
        ...

        app:itemIconTint="@null"
        app:itemTextColor="@null"

        ...

        app:menu="@menu/bottom_nav_menu" />
```
- 코드에 직접 입력
```kotlin
val navView: BottomNavigationView = binding.navView 
        // 틴트를 널값으로 주기
        navView.setItemIconTintList(null)
```
[자세히 보기](https://github.com/Ohleesang/TIL/blob/9a34d889c9a95b744bb13ef0dc8ad087be69f80b/Android/%EB%84%A4%EB%B9%84%EA%B2%8C%EC%9D%B4%EC%85%98%20%EC%95%84%EC%9D%B4%EC%BD%98%20%ED%95%AD%EC%83%81%20%EB%B3%B4%EC%9D%B4%EA%B2%8C%20%ED%95%98%EA%B8%B0.md)

 

## 느낀 점

RecyclerView : 리스트의 형태의 데이터를 효율적으로 관리하고 동적으로 데이터를 보여주고싶을때 좋은 방식인것같다. 

Fragment : 화면간에 이동이 생길때 해당 위치가 변경되지않아서 신기했고 ~~이번에는 화면을 재사용하지는 않았지만,~~ 더 복잡해질 경우 화면 별로 처리할 수 있어 좋은 기술 인것같다.