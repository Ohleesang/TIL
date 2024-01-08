# 해당 위젯에 Fade 애니메이션 넣기
### AlphaAnimation 클래스 이용하여 간단한 페이드 인/페이드 아웃 구현
![floatingButton](https://github.com/Ohleesang/TIL/assets/148442711/86ca9403-da67-4374-a43f-82b3223f9be9)

> 개인과제중 플로팅 버튼이 스크롤위치에따라 페이드인/페이드아웃 애니메이션을 구현하였다.
- 버튼이 제일 위에있을경우, 페이드 아웃 으로 버튼을 숨기고,

- 스크롤 발생시 버튼이 페이드 인 으로 버튼을 보이게 한다.

이때 해당되는 뷰에 애니메이션을 넣는 중 방법이 하나 있는데.
### AlphaAnimation 클래스를 이용하여 구현하였다.

## AlphaAnimation 클래스
View의 투명도를 부드럽게 변경하는 애니메이션 효과를 생성하는데 사용되는 클래스



### 1. 인스턴스 생성
```kotlin
import android.view.animation.AlphaAnimation
import android.view.animation.Animation


val alphaAnimation = AlphaAnimation(1.0f, 0.0f)
```
```kotlin
AlphaAnimation(
    fromAlpha: Float, 
    toAlpha: Float)
```
- fromAlpha : 애니메이션 시작할때의 투명도(Alpha) 
- toAlpha : 애니메이션 끝날때의 투명도(Alpha)

    - 페이드 아웃의 경우에는 1.0 -> 0.0
    - 페이드 인의 경우에는 0.0 -> 1.0
    ```kotlin
    private fun applyFadeAnimation(view: View,isFadeOut:Boolean) {

        val fade : AlphaAnimation = if(isFadeOut) {
            AlphaAnimation(1.0f,0.0f)
        } else AlphaAnimation(0.0f,1.0f)

        ...
    }
    
    ```

### 2. 해당 프로퍼티 및 메소드 정의

```kotlin
private fun applyFadeAnimation(view: View,isFadeOut:Boolean) {

        ...


        fade.duration = 1000 // 페이드 아웃 지속 시간 단위 ms 1000 = 1초
        fade.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {

            ...
            //버튼에 대한 이벤트 처리!

            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })


        view.startAnimation(fade)

    }
```
#### 2-1. 지속시간 설정
- fade.duration : 애니메이션 지속 시간 단위 값(ms) 
    - 1초 = 1000ms

#### 2-2. 리스너 설정
- 'AnimationListener' 인터페이스를 정의한다.
    - onAnimationStart(animation: Animation?): 애니메이션이 시작될 때 호출
    - onAnimationEnd(animation: Animation?): 애니메이션이 끝날 때 호출
    - onAnimationRepeat(animation: Animation?): 애니메이션이 반복될 때 호출

이때 애니메이션이 끝나면 버튼을 보이게하거나 사라지게 설정하였다.
```kotlin
...

override fun onAnimationEnd(animation: Animation?) {

                if(isFadeOut) view.visibility = View.INVISIBLE // 페이드 아웃 후 뷰를 숨김
                else view.visibility = View.VISIBLE
            }

...
```

### 3. 애니메이션 작동
값을 지정한 인스턴스을 실행시킨다.
```kotlin
view.startAnimation(fade)
```

### 4. 해당뷰에 정의한 메소드를 입력
```kotlin
    val floatingBtn = binding.floatingBtnIv
    val isFirstItemVisible =
            linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0
        if(isFirstItemVisible){// 첫번째 위치에 있을시,
            applyFadeAnimation(floatingBtn,true)
            isScrolledStart = false
            }
        else{// 첫번째 위치가 아닐대 최초 한번만 실행!
            if(!isScrolledStart) {
                applyFadeAnimation(floatingBtn,false)
                isScrolledStart = true
            }
        }
```

## 이외의 애니메이션 관련된 클래스들(View Animation)
- TranslateAnimaiton 

    - View를 수평 및 수직으로 이동
```kotlin
TranslateAnimation translateAnimation = new TranslateAnimation(0, 100, 0, 100);
```
- ScaleAnimation

    - View의 크기를 변경
```kotlin
ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f);
```
- RotateAnimation

    - View를 회전
```kotlin
RotateAnimation rotateAnimation = new RotateAnimation(0, 360);
```
- AnimationSet

    - 여러 애니메이션을 하나의 세트로 그룹화하여 병렬(true) 도는 순차적으로 실행
```kotlin
AnimationSet animationSet = new AnimationSet(true); // true는 애니메이션을 병렬로 실행함
```

### 이러한 애니메이션 클래스들은 AlphaAnimation 처럼 똑같은 특징을 가지고있다.
- Animation 클래스를 상속받거나  
- setAnimationListener 메서드를 통해 각각의 애니메이션에 대한 리스너들을 설정
> 즉, 시작,종료 및 중단등의 상태변화에 대응하기위해 리스너를 활용하는게 일반적이다.

### Property Animation
- 더 복잡하고 유연한 애니메이션을 제공
- 객체의 속성을 변화시키는데 . 더많은 제어를 할수 있다.
- 프레임을 계산하여 처리하므로 더 부드러운 애니메이션 처리가능
- ObjectAnimator , ValueAnimator

### ViewPropertyAnimator
- View.animate()
- 간단한 메서드 체인 형태로 애니메이터를 정의할 수있다.
```kotlin
view.animate()
       .translationX(100f)
       .alpha(0.5f)
       .setDuration(1000)
       .start();
```
### Transition Freamwork 
- Transition , TransitionManager
- 화면 전환 효과를 만들 수 있다.
```kotlin
// 뷰의 크기 또는 위치 변경에 대한 전환 효과
val transition = ChangeBounds()
TransitionManager.beginDelayedTransition(parentLayout, transition)

myView.layoutParams.width = 200
myView.layoutParams.height = 200
myView.requestLayout()
```
# 결론
애니메이션 처리가 어떻게 보면 UI를 시각적으로 극대화 할수있는 방식중 하나라 생각한다. 비록 간단하게 알파값을 변경하는 애니메이션(페이드인,페이드아웃)을 추가만했는데도 더 직관적이고 쾌적한 UI를 제공한다. 이번에는 View Animation에 대하여 공부하였지만 나중에 시간이 된다면 세부적인 애니메이션을 건드는  property Animation도 공부해보고싶다.
