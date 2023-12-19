# regex(Relgular Expression)
### 정규 표현식!
- 문자열의 검색,매치 변경에 사용되는 패턴을 정의하는 문자열 
> 특정 문자 조합을 나타내며, 주로 문자열에서 특정 패턴을 찾거나 추출하는데 사용

![regex-example](https://github.com/Ohleesang/TIL/assets/148442711/c74debb7-4d5f-46b0-a3f4-3a3ef6b888c4)

> 상당히 복잡해 보인다..!!

## Kotlin에서의 정규 표현식
### Regex 클래스를 사용하여 정규 표현식을 다룬다.

```kotlin
fun main() {
    // 정규 표현식을 정의
    val regex = Regex("[0-9]+")

    // 대상 문자열
    val inputString = "Hello 123, World! 456"

    // 정규 표현식과 일치하는 부분을 찾음
    val matchResult = regex.find(inputString)

    // 일치하는 부분이 있다면 값을 출력, 없으면 "No match" 출력
    val result = matchResult?.value ?: "No match"
    println(result)
}
```
> Regex 클래스를 정의하여, find(str) 메소드를 이용하여 정규식에 일치하는 문자열을 찾는다.


## 자주 사용되는 정규식 샘플
|정규 표현식|설명|
|--------|---|
|^[0-9]*$|숫자|
|^[a-zA-Z]*$|영문자|
|^[가-힣]*$|한글|
|\\w+@\\w+\\.\\w+(\\.\\w+)?|E-mail|
|^\d{2,3}-\d{3,4}-\d{4}$|전화번호|
|^01(?:0&#124;1&#124;[6-9])-(?:\d{3}&#124;\d{4})-\d{4}$|휴대전화번호|
|\d{6} \- [1-4]\d{6}|주민등록번호|
|^\d{3}-\d{2}$|우편번호|

## 주요 메소드

### 1. find()
- 정규 표현식과 일치하는 첫 번째 부분을 찾는다.
- 반환값은 MatchResult? 이며, 일치하는 부분이 없으면 'null' 을 반환
```kotlin
val regex = Regex("[0-9]+")
val inputString = "Hello 123, World! 456"
val matchResult = regex.find(inputString) // 123
```

### 2. findAll()
- 정규 표현식과 일치하는 모든 부분을 찾는다.
- 반환값은 Sequence<MatchResult> 이며, 각 'MatchResult" 객체는 하나의 일치하는 부분을 나타냄
```kotlin
val regex = Regex("[0-9]+")
val inputString = "Hello 123, World! 456"
val matchResults = regex.findAll(inputString) // (123) (456)
```

### 3. matches()
 - 정규 표현식이 전체 문자열과 정확히 일치하는지 확인
```kotlin
val regex = Regex("[0-9]+")
val inputString = "123"
val isMatch: Boolean = regex.matches(inputString) //true
```

### 4. replace()
-  정규 표현식과 일치하는 부분을 다른 문자열로 교체
```kotlin
val regex = Regex("[0-9]+")
val inputString = "Hello 123, World! 456"
val replacedString:String = regex.replace(inputString, "X") // Hello XXX, World! XXX
```

### 5. split()
- 정규 표현식과 일치하는 부분을 기준으로 문자열을 분할
```kotlin
val regex = Regex("[, ]+")
val inputString = "Hello, World! 123, 456"
val parts = regex.split(inputString)//[Hello,World!,123,456]
```

 6.toPattern()
- Regex 객체를 Pattern 객체로 변환(Java에서 사용되는 클래스)
```kotlin
val regex = Regex("[0-9]+")
val pattern = regex.toPattern()
```
> java에서 제공하는 정규 표현식을 다루기위한 클래스(java의 다양한 라이브러리와 통합할때 유용)
### 정규식 문법 기호
|기호  |설명  |예제        | |
|-----|-----|-----------|--|
| . |임의의 문자 1개를 의미||
| ^ |시작을 의미|^a : a로 시작하는 단어|
| $ |$앞의 문자열로 문자가 끝나는지를 의미|a$ : a로 끝나는 단어|
| [] |[] 괄호 안의 문자가 있는지 확인|[ab][cd] : a,b,중 한 문자와 c,d중 한 문자 | ac ad bc bd|
| [^] |대괄호안에 문자들은 제외|[^a-z] : 알파벳 소문자 제외한 모든 문자|
| - |사이의 문자 혹은 숫자를 의미|[a-z0-9] : 알파벳 소문자 전체,0~9중 한 문자|
|  &#124; |또는 | [a &#124; b] : a 혹은 b 
| () |그룹|01(0&#124;1) : 01뒤에 0 또는 1이 들어간다 | 010(o),011(o),012(x)|
| \b |공백, 탭, ".", "/" 를 의미|apple\b : apple뒤에 공백 탭등이 있다. | banana juice(o)
| \B |\b 의 부정(공백 탭등이 아닌 문자인 경우)| apple\B : apple뒤에 공백 탭이 아닌 문자가 있다.| banana.com(o)
| \d |[0-9]|
| \D |[^0-9]|
| \s |공백, 탭|
| \S |공백, 탭이 아닌문자|
| \w |[a-zA-Z_0-9] 알파벳,숫자,"_"(하이픈)|
| \W |[^a-zA-Z_0-9]|

### 정규식 수량 기호
|기호  |설명  |예제        | |
|-----|-----|-----------|--|
|?|앞의 표현식이 없거나 or 최대 한개만| a1? : 1이 최대 한개만 있거나 없을수도 있다.|a(o),a1(o),a11(x),a111(x)
|*|앞의 표현식이 없거나 or 있거나(여러개)| a1* : 1이 있을수도 없을수도 있다.|a(o),a1(o),a11(o),a111(o)
|+|앞의 표현식이 1개이상 or 여러개|a1+ :1이 1개이상 있다.|a(x),a1(o),a11(o),a111(o)
|{n}|딱 n개 있다|a{3} : a가 딱 3개 있다.|aa(x),aaa(o),aaaa(x)
|{n,m}|n개이상 m개 이하|a{3,5} a 가 3개이상 5개이하 있다.|aa(x),aaa(o),aaaaa(o),aaaaaa(x)
|{n,}|n개 이상|a{3,} : a가 3개이상 있다.|aa(x),aaa(o),aaaaaaaa(o)

### 정규식 그룹 캡쳐 기호
|기호|설명
|--|--
|()|그룹 및 캡쳐
|(?:)|찾지만 그룹에 포함 안됨
|(?=)|= 앞문자를 기준으로 전방 탐색
|(?<=)|= 뒷문자를 기준으로 후방 탐색

```kotlin
val regex = Regex("([0-9]+),([0-9]+)")//각 그룹을 캡쳐
val inputString = "Hello 123,456 World!"
val matchResult = regex.find(inputString)

if (matchResult != null) {
    val group1 = matchResult.groupValues[1] // 첫번째 그룹
    val group2 = matchResult.groupValues[2] // 두번째 그룹

    println("Group 1: $group1")// Group 1: 123
    println("Group 2: $group2")// Group 2: 456

}
```
() 를 이용하면 그룹을 맺어서 처리한다.

# 결론
개발을 하다보면 각 문자열 데이터를 받을 경우가 생길때, 예를들어 이메일,전화번호,주민번호 등등 정해진 문자열을 처리하는 경우가 필요가 생긴다. 이때 정규표현식을 이용하여 구현하면 입력값에대한 처리를 보다 쉽게 구현 할수 있다.
> 복잡한 문자 기호 조합 떄문에 가독성이 떨어지지만,많은 양의 데이터 중에 원하는 데이터를 손쉽게 뽑아 낼수 있고, 그 형식이 맞는지 확인 할 수있다.

표들을 어느정도 한번씩 흝어보고, 만약 코드를 짤때 정해진 문자열을 처리할 경우가 생기면 참고 하면 좋을거같아서 작성하였다. 

