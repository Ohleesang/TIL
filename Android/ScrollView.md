# ScrollView
### 사용자가 화면을 Scroll 할 수 있게 해주는 ViewGroup!
![0_HAOMA9e7060L4Snd](https://github.com/Ohleesang/TIL/assets/148442711/812f0ea4-60ac-4007-8c8d-523e2304d005)
> 제한적인 화면에 구현한 뷰를 넣고싶은데, 그 요소가 화면에 다들어가지않아 scroll로 부족한 부분을 보고싶다!


#### ScrollView는 안드로이드 앱에서 사용자 인터페이스(UI) 요소가 화면에 다 들어가지 않을 때, 사용자가 스크롤을 통해 추가 콘텐츠를 볼 수 있도록 해주는 뷰 그룹.

- 화면에 맞지 않은 콘텐츠를 표시
- 화면에 보이지 않는 부분을 효과적으로 관리

## 주요사항

### 1. 자식뷰는 단하나만 설정가능!
- Scroll ViewGroup을 하나만 설정가능하다
    - 스크롤 이벤트 관리를 효율적으로 가능
    - 보이는 부분만 렌더링 하므로, 성능적으로 이점
    - 하나의 뷰로 지정하면, 레이아웃이 단순화되어 '스크롤 가능하다' 하나만 고려하면된다.
```xml
<ScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <!-- ScrollView 안에 들어갈 여러 뷰들 -->

    </LinearLayout>
</ScrollView>
```
> ScrollView 에 단하나의 뷰 LinearLayout 이 들어가있다.
### 2. 높이 및 너비 설정
- 모든 view 의 공통사항 중 하나!
- 반드시 넓이와 높이가 입력되어야한다.
```xml
<ScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    ...
    </ScrollView>
```
### 3. 기본적으로 수직적인 구조를 지원!
- 수직적(Vertical)인 구조인 경우만 가능하다.
- ### 수평적(Horizontal)인 구조를 원할 경우에는 HorizontalScrollView 를 이용하면 된다! 
    ```xml
    <HorizontalScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <!-- ScrollView 안에 들어갈 여러 뷰들 -->

    </LinearLayout>
</ScrollView>
    ```

## 예제
### 쿠팡이츠 화면을 따라해보자!
[Code 링크](https://github.com/Ohleesang/CoupangEatsApp/blob/804e41679c66cebf0a559ee7eec44fc749d51c2d/app/src/main/res/layout/activity_menu.xml)

### ScrollView(Vertical)
![ezgif_verti](https://github.com/Ohleesang/TIL/assets/148442711/5fe6bb30-109c-4f24-8d52-3aca6264dbf4)

```xml
<ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:paddingLeft="20dp"
            android:paddingRight="20dp">
        
         <androidx.appcompat.widget.SearchView...>
         <TableLayout...>
         <TextView...>
         <Button...>
         <HorizontalScrollView...>
         <ImageView...>
         ...

        </androidx.constraintlayout.widget.ConstraintLayout>
</ScrollView>
```
> 위의 예시들과 달리 constraintLayout을 이용하여 구현하였다.

### HorizontalScrollView(Horizontal)
![ezgif_hori](https://github.com/Ohleesang/TIL/assets/148442711/c5959fc7-b73e-4be1-b2b3-92feef1b733c)
```xml
<HorizontalScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent">
        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:orientation="horizontal">

            <androidx.cardview.widget.widget.CardView...>
            <androidx.cardview.widget.widget.CardView...>
            <androidx.cardview.widget.widget.CardView...>
            <androidx.cardview.widget.widget.CardView...>
            <androidx.cardview.widget.widget.CardView...>

            </LinearLayout>
</HorizontalScrollView>
```
> LinearLayout 을 이용하여 구현.

# 결론
이번 과제를 하면서 UI를 구현하는데 의외로 시간이 많이 걸리는 부분이었다.
> 단순히 스크롤뷰라해서 수직,수평구조를 고려하지않았고, 이로인해 태그값만 변경하는 삽질을 해버렸다..
그리고 구글링의 중요성! 영어로 쳐야 정확하게 내가 원하는 답변을 얻을 수 있다는 교훈도 얻었다!

즉 ScrollView 는, 큰 콘텐츠를 다루거나 화면에 맞지 않은 뷰를 다룰때, 유용한 UI요소 를 구현하는 하나의 ViewGroup 이다. 이때 수직적인경우는 ScrollView,수평적인 경우는 HorizontalScrollView 를 이용하여 구현하면된다.