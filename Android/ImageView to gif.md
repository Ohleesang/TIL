# ImageView to gif
### 안드로이드 스튜디오에서 GIF Image 넣는 방법!

![Image](https://github.com/Ohleesang/TIL/assets/148442711/9060f222-cb08-4499-bfb5-7349cb1541ea)

> 움직이는 이미지를 넣고싶다.. 이때 어떻게 해야할까??

보통 이미지 하면은 drawable 디렉토리 안에 저장하지만 지원하는 확장자는 PNG를 권장하고있다.
움직이는 걸 지원해주는 GIF 같은 경우는 지양하고있다.그러한 이유중 큰 하나가 바로
## 성능적으로 좋은 방식이 아니다.

이 대신 애니메이션에 대한 특화된 리소스 및 클래스(AnimationDrawable,ObjectAnimator)와 같은 API를 이용하여 더욱 성능좋은 애니메이션을 구현할 수 있다.
> GIF 경우에는 파일 크기나, 프레임 제어의 정밀성 부분, 투명도의 처리 에서 제한적이다...

그러므로, 안드로이드 스튜디오에서는 지원은 따로 안해주기 때문에,외부에서 끌어드는 방식을 이용하면 된다. 이걸을 의존성(dependecies) 이라고한다.


## 의존성 'dependencies & Library' 를 추가하여 특화된 위젯을 추가!
> 패키지나 클래스를 참조하는 import 랑 유사하다!

의존성의 경우는, 하나의 소프트웨어나 컴포넌트가 다른 소프트웨어나 컴포넌트에 의존하는 관계
즉, 외부 라이브러리,모듈,프레임워크를 사용할때 쓰이는 개념이다.

## 방법
1. 종속성 추가

![Image](https://github.com/Ohleesang/TIL/assets/148442711/f07c16c3-d104-427a-b653-84db93b5a674)
```kotlin
빌드 디렉토리 에서 두번째에 위치해 있는 
build.gradle.kts(Module:app) 에 들어간다. 
``` 
![Image](https://github.com/Ohleesang/TIL/assets/148442711/e601afd1-1526-4530-bbb2-829b64c2e4b9)
```kotlin
dependencies 안에, implementation("라이브러리 풀네임 : 버젼명")
을 입력한다.
// pl.droidsonroids.gif:android-gif-drawable:1.2.19

이후 프로젝트 상단에 뜨는 파란색 창에 동기화(Sync) 를 클릭한다. 
```
2. ImageView 대신 종속된 GifImageView 사용하기



![Image](https://github.com/Ohleesang/TIL/assets/148442711/d7545c98-0feb-4f96-9c9c-8729549296ff)
```xml
기존에 작성했던 .xml 에서 <ImageView> 태그
``` 
![Image](https://github.com/Ohleesang/TIL/assets/148442711/3e1dd52a-77b8-4e7a-aa48-cab3beed6189)
```xml
<ImageView> -> <pl.droidsonroids.gif.GifImageView> 태그로 변경! 
```

# 결과 
![Image](https://github.com/Ohleesang/TIL/assets/148442711/3f86fd1f-dfa0-4a79-ae6b-bcd51a9fc493)

# 결론
물론 안드로이드에서 gif 확장자를 쓰는걸 지양하는 방식이지만, gif를 단순하게 넣고싶다하면은 나쁜 방식은 아닌거같다. 다만 앱이 무거워 진다면 gif 파일의 프레임의 단위로 짤라서, AnimationDrawable 이나 ObjectAnimator를 이용하여 처리하는게 훨씬 효율좋게 처리할 수 있다고 생각한다.