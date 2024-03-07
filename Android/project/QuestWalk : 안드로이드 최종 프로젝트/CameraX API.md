# CameraX API
### 앱 내의 카메라를 구성하고 카메라 기능을 실행하고 싶을때!

![1_2eT4Nqh-6u6yVzg5Tenw5Q](https://github.com/Ohleesang/TIL/assets/148442711/cdb78e11-f0bd-4670-bcbe-7db4d254b36f)

## CameraX 란?
- CameraX는 Android Jetpack의 일부로, Android 카메라 앱 개발을 단순화하는 라이브러리
- Intent를 이용해 Camera App을 불러 들이는게 아니라 
직접 앱 내에서 카메라 기능을 개발하고 싶을때 사용하기위한 라이브러리 중 하나이다. 

### Camera2 vs CameraX
| 기능 | Camera2 | CameraX |
|------|---------|---------|
| 사용 용이성 | 저수준에서 카메라를 제어 | 추상화하여 간단하게 카메라를 제어 |
| 생명주기 | 직접 관리 | 자동으로 관리 |
| 호환성 | API 레벨 21 이상 | API 레벨 21 이상에서 사용 가능, 다양한 기기에서 일관된 동작을 보장 |
| 사용 사례 | 고급 카메라 기능이 필요할 때 유용 | 일반적인 카메라 기능을 쉽게 구현|


### 결론
세부적인 기능 즉, Low-Level 카메라 앱을 구성한다면 Camera2, 가볍고 기본적인 기능을 구성할려면 CameraX 를 선택하는게 바람직하다.
### 특히 피지컬 디바이스가 없는 사람에 입장에서는 그냥 CameraX를 사용하는걸 적극 권장한다.
> 본인의 경우 처음에 Camera2로 구성했는데 복잡하기 하고 이해하는데 시간이 많이걸리고.. 막상 가상머신 으로는 잘되지만 핸드폰으로는 잘안되는경우가 발생하기도하고해서 변경 하였다. ~~아까운 내시간 돌려줘 ㅠ~~

## 구성 및 구현
기본적으로 필요한 개념들을 정리하자면,

#### 1. ProcessCameraProvider
- 카메라의 생명주기를 관리하고 Use Cases를 바인딩
#### 2. Use Cases
- 카메라의 기능을 나타내는 클래스
  - PreView : 미리보기
  - ImageCapture : 사진촬영
  - ImageAnalysis : 이미지 분석
#### 3. CameraSelector
- 사용할 카메라를 선택
  - CameraSelector.DEFAULT_BACK_CAMERA : 후면 카메라
  - CameraSelector.DEFAULT_FRONT_CAMERA : 정면 카메라
### 4. Executor
- 카메라의 설정 및 기능을 실행할떄 비동기적으로 처리하기 위해 쓰레드를 지정하고 관리
### 1. 카메라 설정 및 연결하기(Preview 구성)
ProcessCameraProvider 의 인스턴스를 생성 -> 카메라를 설정하는 리스너를 등록하여 호출 -> Use Cases 생성
-> Use Cases 바인딩 -> 바인딩중에 예외 처리 구성(카메라의 기능을 지원할수없거나 접근이 불가능할때)

```kotlin
private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
        cameraProviderFuture.addListener(
            setCamera(cameraProvider),
            ContextCompat.getMainExecutor(requireContext())
        )

private fun setCamera(cameraProvider: ProcessCameraProvider): Runnable = Runnable {
        val preview = Preview.Builder()
            .build()
            .also { mPreview ->
                mPreview.setSurfaceProvider(
                    binding.pvPreview.surfaceProvider
                )
            }

        imageCapture = ImageCapture.Builder()
            .build()

        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                this, cameraSelector, preview, imageCapture
            )
        } catch (e: Exception) {
            Log.d("CameraX", "startCamera Fail", e)
        }
    }
```
### 2. 캡쳐 구성(ImageCapture)
imageCapture.takePicture 으로 콜백함수 지정 -> 해당 콜백함수 기능 실행
```kotlin
private fun capturePhoto() {
        val imageCapture = imageCapture ?: return
        imageCapture.takePicture(
            ContextCompat.getMainExecutor(requireContext()),
            imageCaptureCallback()
        )
    }

    private fun imageCaptureCallback(): ImageCapture.OnImageCapturedCallback {
        return object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                //이미지 처리(비트맵 형식으로 저장)
                //다음 프래그먼트에 전달 ImageView에 보여주기)
                image.close()
            }
        }
    }
```
### 3. 받은 이미지 처리(ImageProxy to Bitmap)
카메라 이미지의 데이터를 바이트 배열로 가져와 비트맵 형식으로 전환
- ImageProxy : 사진이나 비디오 프레임을 나타내는 인터페이스
- Bitmap : 안드로이드에서 이미지를 표현하는 클래스
```kotlin
val buffer = image.planes[0].buffer
val bytes = ByteArray(buffer.remaining())
    buffer.get(bytes)
val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
// 이미지를 전달 or ImageView 에 등록
```