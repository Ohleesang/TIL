#  registerForActivityResult()
### Registor for Activity Result
Activity(액티비티)의 Result(결과 값)을 위한 Registor(레지스터)

- Registor(레지스터)?
    >브로드캐스트 리시버,센서,액티비티 간의 결과 처리 등 다양한 기능에서 특정 이벤트를 감지하거나 처리하기 위해 등록하는 과정
## 다른 액티비티로부터 결과를 처리하기 위한 것!
> 두번째 액티비티로 부터 어떤 데이터나 특정 결과를 기대할 때 사용된다.




- 대략적인 코드 구조
![스크린샷 2023-12-14 오후 8 36 03](https://github.com/Ohleesang/TIL/assets/148442711/c21d250b-cba1-4cec-b43a-4105657ec00c)

1. registerForActivityResult() 함수를 생성
    - 데이터를 주고받기위해 사용 되는 AcitivityResultLancher 객체를 생성하고 반환
    - 이 함수는 특정한 컨트렉트(StartActivityForResult)에 따라 동작하며 해당 컨트렉트에 대한 AcitivityResultLancher 를생성
    - 이 떄 ,값이 들어왔을 경우를 처리.

2. intent 생성 이후, 2번째 액티비티를 실행

3. 2번째 액티비티에서 데이터 처리 이후 intent()에 데이터를 넣음

4. setResult(Intent)로 인텐트를 첫번째 액티비티에 전달

5. 값을 받아서 그대로 함수 처리.
#### 예제 코드
```kotlin
// 첫 번째 액티비티에서
val someActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
    if (result.resultCode == Activity.RESULT_OK) {
        // 두 번째 액티비티로부터 결과를 처리
        val resultData = result.data?.getStringExtra("key_name")
        // 결과 데이터를 사용
    } else if (result.resultCode == Activity.RESULT_CANCELED) {
        // 사용자가 작업을 취소한 경우 처리
    }
}

fun startSecondActivity() {
    val intent = Intent(this, SecondActivity::class.java)
    //startActivity(intent)
    someActivityResultLauncher.launch(intent)
}
```
```kotlin
// 두 번째 액티비티에서
val result = Intent()
result.putExtra("key_name", "일부 결과 데이터")
setResult(Activity.RESULT_OK, result)
finish()
```

## 왜 사용하냐?
기존 Intent에 값을 전달하는 경우, 다른 액티비티로 이동하고 그곳에서 어떤 작업을 수행한 후, 그 결과를 새로운 Intent에 담아 다시 첫 번째 액티비티로 이동할 수 있다. 
### 그러나 이렇게 하면 복잡성이 증가하고 불필요한 Intent가 많아질 수 있다.
그러나, setResult 및 startActivityForResult를 사용하면 다른 액티비티에서의 작업이 완료되면 결과를 돌려받아 처리할 수 있으므로, 일반적으로 두 번째 액티비티에서의 작업이 완료된 후에만 결과를 전달하게 되어진다. 이는 액티비티 간의 흐름을 더 명확하게 만들 수 있다.

### 즉, 'startActivityForResult' 및 'setResult' 를 사용하는 것은 액티비티 간의 특정 작업과 그 결과를 편리하게 처리하기 위한 방법이다!

# 결론
데이터를 전달할때 intent를 보내고 거기서 또 intent를 생성하고 하면 여러개의 intent가 생성되어 오히려 복잡해지지만,특정한 경우(두 액티비티 간의 데이터 전달)의 레지스터를 이용하여 하는 방식을 이용하니까 불필요한 intent을 생성안해도 되어서,더 최적화된 방식인거같다. 