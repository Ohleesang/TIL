# API 보안
### API 가 노출되거나 악용되는걸 방지!
API는 애플리케이션 간에 데이터를 교환하는 방법을 제공하므로, 

잘못 구성되거나 보호되지 않으면 중요한 정보가 노출되거나 악의적인 공격에 취약 해질 수 있다.

서버에서 API 보안을 강화할 수 있고, 앱 내에서도 보안을 강화 할 수 있는 방법이 존재한다.

이번 글은 앱 개발자 기준으로 작성 하였으며, 대략적인 API 보안 강화를 하는 방법을 작성 할려고한다.
## 앱 개발자 로써의 API 보안을 강화하는 방법
1. API KEY 보관
  - API KEY 를 소스 코드에 포함시키지않는다.
  - gradle.properfies 파일 혹은 local.properties 과 같은 별도의 파일에 키를 저장하고, 참조 하는 방식으로 구현
2. HTTPS 사용
  - Android 9(API 28) 부터 기본적으로 HTTP 트래픽은 차단
  - HTTPS 는 기존 HTTP 트래픽 에 보안성이 강화 된것
  - 왠만하면 HTTPS 를 제공하는 API 를 쓰는게 적절하다.
3. 데이터 검증
  - 사용자로부터 받은 데이터와 서버로 받은 데이터를 항상 검증
  - 입력된 데이터가 특정 기준에 부합하는지 확인
    > 유효성 검사같이 양식에 맞춰서 데이터를 받아들임(ex. email는 @ or . 기호가 반드시 있어야 한다.)
4. 최소 권한 원칙 적용
  - 앱이 필요로 하는 최소한의 권한만 요청
  - 불필요하게 많은 정보에 접근하는걸 방지
5. 에러 처리
  - 앱이 에러를 반환할때, 과도한 정보를 사용자에게 노출하지 않도록 주의
  - 예외가 발생시 사용자에게 이를 알려야하므로 너무 자세히는 알려주지는 말 것
6. 보안 업데이트
  - 사용하는 라이브러리,프레임워크 는 최신버젼으로 보안 업데이트를 항상 적용해야함


직접 코드를 작성하면서 지키는 규칙 이외 환경 설정을 해야하는데, 가장 중요한 API KEY 보관하는 방법을 소개하고자 한다.
> 누가 내 API KEY 를 쓰면 무서우니까!!(하루 일일 제한도 있고해서..)

## API KEY 보관 방법
### 환경 변수 설정 방법
locals.properties 에 저장 하는 방식!
- locals.properties 는 로컬 혹은 서버에서만 설정되어지며,소스코드는 여기에있는 정보를 참조하여 API KEY 값을 불러들일 수 있다.

#### 1. local.properties 에 내 API KEY 정보 입력하기
<img width="450" alt="스크린샷 2024-02-01 오후 7 48 40" src="https://github.com/Ohleesang/ImageSearchPageApp/assets/148442711/e94c6420-fa2b-4ae5-8ba6-9d09279259e5">

- 내 컴퓨터(Local) 에만 저장되는 파일이며 git 에 커밋할때 자동으로 제외된다.

#### 2. 환경 변수로 쓰기위해 값을 Load

```groovy

import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

// local.properties 에서 API 키를 로드
val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { localProperties.load(it) }
}
val apiKey = localProperties["DAUM_SEARCH_API"] ?: ""
```
[build.gradle.kts(:app)]

- 저장해두었던 API KEY 값을 빌드 과정에서 값을 가져오는 코드
- 외부파일 에서 저장한 문자열을 apiKey 값에 저장한다.

#### 3. BuildConfig 객체에 프로퍼티 추가

```groovy
android {
    ...

    defaultConfig {
        ...

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //BuildConfig 클래스에 추가하여 전역변수로 사용가능
        buildConfigField("String", "DAUM_SEARCH_API", "$apiKey")
    }
    ...
    buildFeatures {
        viewBinding = true

        buildConfig = true
    }

```
- BuildConfig 
  - Android 빌드과정에서 자동으로 생성되는 클래스!
  - Sync 혹은 rebuilde를 하면 해당 클래스에 API KEY 문자열이 하나 추가된다.


<img width="677" alt="스크린샷 2024-02-01 오후 7 58 54" src="https://github.com/Ohleesang/ImageSearchPageApp/assets/148442711/13688ad5-3aa4-4961-9343-8ba0497a31b7">
[BuildConfig.java]

#### 4. 소스 코드에 API KEY 값 참조하기
- BuildConfig.DAUM_SEARCH_API 를 불러 들이면 완성!

```kotlin
interface DaumSearchApi {
    companion object {
        const val REST_API_KEY = BuildConfig.DAUM_SEARCH_API

    }

    ...
}
```
- 서버에 보낼 쿼리를 구성하는 인터페이스
- BuildConfig 에 저장된 API_KEY 를 불러들어 API_KEY 값을 참고한다.

### 설정 파일 사용방법

#### 1. gradle.properties 파일에 API 키 저장
  
   ```
   MyApiKey="xxxx....."
   ```
   [gradle.properties]

   - 빌드 도구 설정을 정의하는 파일
   - key-value 형식으로 데이터를 저장   
   
#### 2. .gitignore에 추가하여 소스 코드 관리 시스템에 포함되지 않도록 한다.
   - 디렉토리 보여주는 부분 왼쪽 좌상단에 Android -> Project
   - .gitnore 파일에 gradle.properties 추가

```.gitnore
*.iml
.gradle

...

.cxx
local.properties
gradle.properties
```
[.gitnore]
 - 여기에 작성한 파일들은 git 버전 관리 대상에서 제외됨

#### 이후 3,4 번은 위에 작성한 내용대로 같다.
- BuildConfig 객체에 프로퍼티 추가
- 소스 코드에 API KEY 값 참조하기
