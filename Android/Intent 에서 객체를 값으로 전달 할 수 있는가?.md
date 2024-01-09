# Intent 에서 객체를 값으로 전달 할 수 있는가?
## Yes! 그러나 직렬화(Serialization)란 개념을 알아야한다!
![serialization-diagram-800x364-1](https://github.com/Ohleesang/TIL/assets/148442711/8398b9f2-7594-4162-be90-4848f85a3e86)
> 직렬화(Serialization)의 다이어그램

### 기본적으로, Intent 를 사용하여 다른 액티비티로 전송할때, 데이터 유형은 일반적으로 Android에서 지원하는 기본 데이터 유형이어야한다.

즉, 사용자가 정의한 클래스나,직렬화되지않은 객체를 직접 Intent에 넣는것은 불가능하다.

## 직렬화(Serialization)란??
### 데이터 구조나 객체를 다른 시스템에서 사용하거나 저장하기 위해 일련의 바이트로 변환하는 과정.
![serialization-deserialization-diagram-800x318-1](https://github.com/Ohleesang/TIL/assets/148442711/21bb19a5-cfc7-4d42-9e05-16ac23dfdbff)

데이터를 바이트화 하여 통신후, 수신할떄 바이트값을 받고, 역직렬화(Deserialization)를 통해 처음의 데이터 구조나 객체로 복원할 수있다.
- 네트워크 통신, 파일 저장,데이터베이스 저장 등에 활용된다.

## 왜 사용하는가?
### 전반적으로 객체나 데이터의 영속성(지속성)을 확보할수 있으므로, 다양한 환경에서 데이터를 공유하는데 사용되어진다.


1. 데이터 전송 및 네트워크 통신
    - 바이트 스트림(데이터를 바이트 단위로 전송하는 입출력 스트림)을 이용하면 유용하다.
2. 데이터베이스 저장
    - 데이터 지속성을 확보할수 있으므로 유용.
    - 장기적으로 데이터를 보존할때 유리함.
3. 애플리케이션 간 통신
    - 데이터를 포맷화하고 전송.
    - 서로 다른 플랫폼이나 프로그래밍 언어 간에 상호 운용성을 제공하는데 도움
4. 객체 저장 및 복원
5. 캐시 구현
    - 메모리 사용을 효율적으로 관리가 가능.

즉, 데이터 간에 통신이 필요한 경우, 직렬화를 하여 바이트스트림 형태의 데이터를 공유하면, 성능과 메모리 효율성에 유리하다!

## Kotlin 에서의 직렬화
Kotlin에서 객체를 데이터로 전송하기 위하여 크게 2가지 방식의 인터페이스가 있다.
### 1. Serializable
- interface 구현 방식
    - Java 표준 라이브러리에 포함된 인터페이스
    - 마킹 인터페이스(따로 메소드 구현 할 필요 없음)
- Reflection 사용
    - 프로그램 실행중, 클래스이 정보를 얻거나,필드,메서드,생성자등을 동적으로 조작 가능
    - 이로인해 성능 손실을 야기 할 수 있음
- Java 직렬화 프로토콜 사용
    - Java 에서만 이해할 수 있는 형식을 사용
    - Kotlin은 자바 언어랑 100% 호환 되기떄문에 사용가능


### 2. Parcelable
- interface 구현 방식
    - Android 에서 제공하는 인터페이스
    - writeToParcel 및 createFromParcel 같은 몇가지 메소드를 구현할 필요
- Reflection 미사용
    - 명시적인 메서드를 제공하여 성능적으로 좋음
- 안드로이드 특화
    - Android 플랫폼에 특화된 인터페이스


Java 표준을 따르는 일반적인 경우에는 Serializable 를 이용하고 기본적으로 성능면에서는
Parcelable 이뛰어나므로 Android 개발환경일때는 후자를 더 추천한다. 

## 예제
#1 Person 클래스

- Parcelabe 로 구현
```kotlin
import android.os.Parcel
import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class Person(val name: String, val age: Int) : Parcelable
//Data Class 이므로 따로 메소드는 재정의 할 필요없다.
```

- Serializable 로 구현
```kotlin
import java.io.Serializable

data class Person(val name: String, val age: Int) : Serializable
```


#2 MainActivity

```kotlin
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Parcelable
        val personParcelable = Person("John (Parcelable)", 30)
        val intentParcelable = Intent(this, SecondActivity::class.java)
        intentParcelable.putExtra("person", personParcelable)
        buttonParcelable.setOnClickListener {
            startActivity(intentParcelable)
        }

        // Serializable
        val personSerializable = Person("Jane (Serializable)", 25)
        val intentSerializable = Intent(this, SecondActivity::class.java)
        intentSerializable.putExtra("person", personSerializable)
        buttonSerializable.setOnClickListener {
            startActivity(intentSerializable)
        }
    }
}


```

#3 SecondActivity
```kotlin
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Parcelable
        val personParcelable = intent.getParcelableExtra<Person>("person")
        textViewParcelable.text = "Parcelable: ${personParcelable?.name}, ${personParcelable?.age}"

        // Serializable
        val personSerializable = intent.getSerializableExtra("person") as Person
        textViewSerializable.text = "Serializable: ${personSerializable.name}, ${personSerializable.age}"
    }
}
```

# 결론
각 activity에서 데이터를 공유할때 쓰이는 Intent를 객체로 전달하는 방법을 알았다.
즉, 인터페이스 parcelable 를 상속받아 처리하면, 보다 쉽게 처리할 수 있다. 

왜 직접적인 객체전달을 못하게하는지 이해가 되었고, TIL를 작성하여 개념을 정리하다보니 구조를 이해하게되어 코드 작성을 더 잘할 수 있을것 같다.