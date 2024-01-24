# Lottie Animation
### 성능과 화려함의 두가지 토끼를 잡을수 있는 애니메이션!
![LottieAnimation](https://github.com/Ohleesang/TIL/assets/148442711/a24b2a9f-9972-49fa-b26f-9dd59b280d5d)


[앱에 실제 적용 화면]

Airdnb에서 개발한 오픈소스 라이브러리로,Adobe Atfer Effects 같은 도구로 사용해 만든 애니메이션을 JSON 형식으로 내보내어 앱에서도 쉽게 사용 가능하다.

## 특징
- 백터 애니메이션을 지원하므로 크기를 변경해도 크게 품질 변화가 없다
- 애니메이션의 진행상태를 코드로 제어할 수 있다
- JSON 파일 하나로 쉽게 애니메이션을 구현할 수있으므로, 범용성 및 성능적으로 이점이 크다.

## 구현
### 1. [Lottie](https://lottiefiles.com) 웹사이트를 방문해 자기가 원하는 JSON 파일을 다운로드 받는다.
![aaa](https://github.com/Ohleesang/TIL/assets/148442711/641c530b-afde-4f07-845b-7056d2699fc8)

[애니메이션.gif]

- 더 화려한 애니메이션을 구하고싶으면 결제를 해야한다...!
- 하지만 무료로도 이쁜 샘플들도 많으니까 한번 찾아보자


### 2. 받은 JSON 파일을 자기가 개발하고있는 앱의 디렉토리 asset(혹은 raw) 생성하여 넣는다.
<img width="225" alt="스크린샷 2024-01-24 오후 8 41 34" src="https://github.com/Ohleesang/TIL/assets/148442711/cf30963e-e47d-46e2-ba53-1813851ea0c6">


### 3. 해당 기능을 이용하기위해 종속성에 추가한다.
```kotlin
dependencies {
    ...
    //lottie 애니메이션 추가
    implementation("com.airbnb.android:lottie:4.1.0")
}
```
### 4. 애니메이션에 쓰일 레이아웃에 LottieAnimationView 를 추가한다.
```xml

    ...

    <androidx.constraintlayout.widget.ConstraintLayout
        android:id="@+id/cl_item"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="10dp">


        <com.airbnb.lottie.LottieAnimationView
            android:id="@+id/lav_heart"
            android:layout_width="50dp"
            android:layout_height="50dp"
            android:layout_marginTop="8dp"
            app:lottie_rawRes="@raw/heart_animation"
            app:lottie_autoPlay="false"
            app:lottie_loop="false"
            app:layout_constraintEnd_toEndOf="@+id/iv_thumb_nail"
            app:layout_constraintTop_toBottomOf="@+id/iv_thumb_nail" />

            ...


        </androidx.constraintlayout.widget.ConstraintLayout>
```
- lottie_rawRes : JSON 파일의 위치를 저장
- lottie_autoPlay: 해당 애니메이션을 자동재생 할것인가
- lottie_loop : 해당 애니메이션을 반복해서 재생 할것인가

### 5. 세부적으로 애니메이션을 제어할려면 코틀린 코드로 가능하다.
```kotlin
 override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = mItems[position]
        
        ...

        holder.cardView.setOnClickListener {
            holder.likeAnimation.playAnimation()
        }
    }
```
해당 뷰홀더에서 카드뷰를 클릭시,애니메이션을 재생하도록 설정 하였다.

## 결과
![lottie](https://github.com/Ohleesang/TIL/assets/148442711/299cb14c-0915-4dc7-8908-95e6d360f284)

# 결론
예전에 움직이는 애니메이션을 이용할때 gif 파일을 연결하는 방식을 사용했는데,이번에는 lottie 외부 라이브러리를 사용하여 애니메이션을 구현해보았다. 막상 사용해보니 큰 어려움은 없으며 오히려 세부적으로 애니메이션을 조절(역재생을 한다던가,속도를 조절한다던가)를 할 수 있어 더 역동적인 앱을 구현할 수 있다.