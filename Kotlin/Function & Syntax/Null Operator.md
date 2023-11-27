# 널 관련 연산자들
-kotlin에서 null 관련해서 안전하게 처리할 수 있는 몇몇 연산자가 있다.


# Safe Call 연산자('?.')
- 객체가 null 이 아닌 경우에만 해당 속성이나 메서드를 호출
ex) val str: String? ="abc"
val length: Int?=str?.length
//3

# 엘비스 연산자('?:')
- 'null'체크를 간단하게 수행하는데 사용
 왼쪽 피연산자가 null 이 아니면, 왼쪽 피연산자 값이 반환되고,
 왼쪽 피연산자가 null 이면 오른쪽 피연산자의 값이 반환된다.

 ex) val nullValue :String? = null
    val value :String? = "Hello"
    val res1 = nullValue ?: "비어있는 값"
    val res2 = value ?: "비어있는 값"
    println(res1)
    println(res2)
    //비어있는값 : res1 결과
    //Hello : res2 결과

# Non-null('!!') 단언 연산자 
- 이 연산자는 특정 표현식이 'null'이 아님을 개발자가 
확신할때 명시해주는 연산자. 
하지만 null이 들어가 있다면 예외가 발생한다.(NullPointerException)

 ex) val nullValue :String? = null // 널값 입력
     val no_nullValue: String = nullValue!! //널값이 아니라고 명시
     println(no_nullValue) // NullPointerException 예외발생!

# Safe cast 연산자('as?')
- 안전한 형 변환을 수행,형 변환이 실패하면 null 반환

 ex) val count: Int? = str?.length as? Int 
    println(count)
    //3


# 결론
Kotlin 은 안정성을 강조하는 언어인걸 알고 있었지만 
 클래스로 이진트리를 구현하거나, 앱에서 함수 처리할때 항상 예외 처리를 고려
 해야한다는걸 느꼈다. 이러한 연산자를 이용하여 보다 간단히 코드의 안정성을 
 높일수 있다. 