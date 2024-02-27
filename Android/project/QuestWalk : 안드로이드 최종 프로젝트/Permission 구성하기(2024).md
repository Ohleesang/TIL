# Permission 구성하기(2024)
### 시스템 혹은 다른앱의 보호된 부분에 Access 하기위해 허용하는것을 의미!
개인 정보에 영향에 따라 크게 두가지로 나뉜다.
- 일반 권한
  - 개인정보에 영향 x
  - 인터넷 , 진동 설정, 네트워크(wifi) 확인 등등
- 위험 권한
  - 개인정보에 영향 o
  - 사용자의 동의없이 비용이 발생할 수 있는 작업
  - 카메라 사용, 위치정보접근, 연락처 접근, 마이크 사용 등등

## 권한 등록

매니페스트 파일(AndroidManifest,xml) 에 <uses-permission> 태그를 사용하여 해당 권한을 선언하면 사용 가능
```xml
<manifest xmlns:android= ...>
...

<uses-permission android:name="android.permission.READ_CONTACTS" />

...
<application>
```

### 하지만, 위험권한의 경우 앱이 실행되는 동안 사용자에게 권한 요청 대화상자를 표시하여 사용자의 동의를 얻어야한다.

## 위험 권한 등록
camera 를 등록하는 방법을 예시로 설명하겠다.

### 1. Manifest 파일 등록
camera 의 경우, 하드웨어 기능을 이용하기 때문에 따로 <uses-feature> 태그가 필요하다.
```xml
<manifest xmlns:android= ...>
...
<uses-feature
        android:name="android.hardware.camera"
        android:required="false" />

<uses-permission android:name="android.permission.CAMERA" />
...
<application>

```
- android:required ="false"
  - 앱을 사용할수 있지만,카메라가 없는 장치에서도 설치하고 실행 할수 있다.
### 2. permission 등록 콜백 함수 정의

액티비티 간의 데이터 전달하고 결과값을 받을때 쓰는 함수가 있었다.

#### ActivityResultLauncher 라는 함수 인데, 
권한 자체는 액티비티에서 구성하고 이루어지므로 마찬가지로 콜백함수를 등록하면 간편하게 권한관리가 가능하다.

```kotlin
private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                setCamera() // 기능을 실행
            } else {
                Snackbar.make(requireView(), "권한 받아오기 실패", Snackbar.LENGTH_SHORT).show()
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
```
- 프래그먼트 내에 정의 한것이므로 requireActivity()를 이용
### 3. permission 확인 하기
화면(액티비티 or 프래그먼트)이 생성 될때 permission이 있는지 없는지 판별하는 기능이 필요하다.
- permission 이 있다면, 기능을 실행
- permission 이 없다면, 권한 설정을 하는 다이얼로그 출력

```kotlin
private fun checkPermissions() {
        if (
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // 권한이 있다면 기능을 실행(카메라 등록)
            setCamera()
        } else {
            //권한이 없다면 launch 를 이용하여 다이얼로그를 출력(콜백함수 부르기)
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
```
## 결과
<img width="315" alt="스크린샷 2024-02-27 오후 9 38 30" src="https://github.com/Ohleesang/TIL/assets/148442711/57633741-55d4-4f25-bf26-8970594fdb12">


## 주의사항
만약, 허용안함을 눌렀을 경우에는, 해당 기능(카메라)가 필요할경우 항상 권한이 거부되므로
수동적으로 설정에 들어가서 권한을 설정해야한다!