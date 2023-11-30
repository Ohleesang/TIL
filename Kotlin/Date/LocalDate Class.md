# LocalDate Class(날짜 클래스)
## 날짜(Date)를 다루는 클래스
> 기존 Date Class보다 항상 된 기능을 제공.
> Date Class와 다르게 날짜만을 다루기 위한 클래스
```kotlin
import java.time.LocalDate

val current :LocalDate  =LocalDate.now()
//현재 날짜를 초기화
val date :LocalDate = LocalDate.of(2021,11,30)
//특정 날짜(2011,11,30)를 초기화

```

## 왜 Date Class 보다 좋은가
### Date Class 의 단점
- 가변성
    > 보통 날짜 같은 일정한 값에 변경이 가능하면, 코드가 불안정 해진다. 
- 논리적 에러 가능성
    > 월(Month) 의 경우, 1 부터시작하는데 이 클래스는 0부터 시작하여 혼동성을 준다.
- 쓰레드 안정성 문제
    > 가변성으로 인해, Thread에서 안정성이 없다.  
- API의 한계
    > Date 는 날짜와 시간을 동시에 다루기 떄문에 더 복잡하다
- Java 8 이전의 API
    > 너무 옛날에 만들어진 클래스이다. 이로인해 java.time 에서 더 나은 클래스를 제공한다.


### 그러므로, Date Class 의 단점을 보완하여, 
### 더 나은 환경을 제공하는 LocalData Class 를 쓰는것을 권장한다!

## Kotlin에서 LocalDate Class

```kotlin
import java.time.LocalDate
//java.util.LocalDate 에서 Import! 

var current :LocalDate =LocalDate.now()
//현재 날짜를 초기화

var date :LocalDate = LocalDate.of(2021,11,30)
//특정 날짜(2011,11,30)를 초기화
```
> 클래스를 선언하는 방식은 총 2가지

## Method(기능)

1. getYear(),getMonthValue(),getDayOfMonth()
    - 각각 연도,월,일을 반환
```kotlin
var year :Int  = date.getYear() // 2021
var month :Int = date.getMonthValue() // 11
var day :Int = date.getDayOfMonth() // 30

```

2. plusDays(long daysToadd),minusDays(long daysToSubtract)
   
    - 날짜에 일수를 더하거나 뺴는 메소드
```kotlin
var future :LocalDate = date.plusDays(7) 
//2021-12-07
var past :LocalDate = date.minusDays(7)
//2021-11-23
```
cf ) plusMonths,minusMonths 도 마찬가지로 적용가능.


3. isBefore(LocalDate other),isAfter(LocalDate other),isEqual(LocalDate other)
   
    - 날짜를 비교하는 메소드
```kotlin
var before :Boolean = date.isBefore(current) // false

var after :Boolean = date.isAfter(current) // true

var equal :Boolean = date.isEqual(current) // false
```

4. withYear(int year),withMonth(int month),withDayOfMonth(int dayOfMonth)
    - 해당 필드를 새로운 값으로 변경한 LocalDate 객체를 반환
``` kotlin
var newYear :LocalDate= date.withYear(2034)
// 2021-11-30 >> 2034-11-30 으로 변경됨

```

5. isLeapYear() 
    - 윤년인지 여부를 반환
```kotlin
var leapYear :Boolean = date.isLeapYear()
//false 
```

6. format(DateTimeFormatter formatter)
    - 지정된 형식으로 날짜를 문자열로 변환

```kotlin
import java.time.format.DateTimeFormmater

var dateFormatPattern ="yyyy-MM--dd"
var dateFormatter: DateTimeFormatter =
DateTimeFormatter.ofPattern(dateFormatPattern)

var formattedDate : String = date.format(dateFormatter)
//2023-11-30


```

7. parse(CharSequence text)
 - 문자열을 'LocalDate' 객체로 파싱한다.
 ```kotlin
 var parsedDate :LocalDate = LocalDate.parse("2023-11-30")
 
 ```

## 파싱(Parsing)이란?
- 일련의 문자열을 읽어서 원하는 형식이나 구조로 변환하는 과정
- 날짜 및 시간을 다룰때 중요한 개념. 시스템이 이해할 수 있는 형식으로 변환

### 파싱의 일반적인 단계
1. 텍스트 읽기 
> 변환할 대상인 텍스트를 읽는다.
2. 구문 분석
> 텍스트를 문법 규칙에 따라 분석,의미있는 부분을 추출
3. 데이터 변환
> 추출된 정보를 프로그램이나 시스템에서 사용할 수 있는 형식으로 변환

위의 6,7번처럼 문자열에서 LocalDate를  저장하기 위해 DateFommater 객체를 이용하여 파싱을 진행 할 수 있다.



# 결론
추가 과제,호텔 프로그램을 작성하는데 프로퍼티(property)으로 날짜가 있어서 이번 Date 객체를 알아보고 썼는데, 구글링을 해보니 LocalDate를 쓰는걸 권장하더라. 한번 써보니 별차이는 없는거 같지만, 서버에 데이터를 보내거나 여러 쓰레드로 프로그램이 실행될때, LocalDate 클래스가 효율적이니까 Date 보다 더 익숙해지는게 좋은거 같다.