# StringBuilder 란?
-StringBuilder 란, 가변 문자열을 생성하고,수정하는데 사용되는 클래스.
문자열을 효율적으로 조작할 수 있도록 설계되어있어, 
문자열을 여러번 수정 해야할때 유용하다.
즉, 문자열을 변경할때, 새로운 문자열을 생성하는 것보다 보다 효율적이다.

``` kotlin
var stringBuilder = StringBuilder()
//가변 문자열인 stringBuilder 빈 클래스를 선언
```
이 클래스에 자주 쓰이는 함수들은 이렇다.
 
 1.append(value: Any?)
 - 문자열 추가 함수. 이때 인자값은 
 문자열,숫자,다른 데이터를 추가할 때 사용 한다.
  ``` kotlin
  ex1)
    stringBuilder.append("Hello, ").append("World!")
    //Hello, World!
    
  
  
  ex2) 
    charArr = charArrayOf('a','b','c')
    stringBuilder.append(charArr)
    //abc
  
  ex3)
    intArr = intArrayOf(1,2,3)
    stringBuilder.append(intArr)
    //123
```
2.insert(index:Int,value:Any?)

- 특정 위치(index)에 값을 삽입
  ``` kotlin
  ex) 
    stringBuilder = StringBuilder("Hello World!")
    stringBuilder.insert(7," Beautiful ")
    //Hello, Beautiful World!

3.delete(start:Int,end:Int)
- 지정된 범위의 문자를 삭제
  ``` kotlin
  ex)
    stringBuilder = StringBuilder("Hello, Beautiful World!")
    stringBuilder.delete(7, 16)
    //Hello, World!

4.replace(start:Int,end:Int,str:String)
- 지정된 범위의 문자를 주어진 문자열로 대체
  ``` kotlin
  ex) 
    stringBuilder = StringBuilder("Hello, World!")
    stringBuilder.replace(7, 12, "Beautiful")
    //Hello, Beautiful!

5.toString()
- 가변형 문자열인 stringBuilder를 불변 문자열 String 으로 변환
  ``` kotlin
  ex) 
    result: String = stringBuilder.toString()
    ..toCharArray() 도 가능하다

6.substring(startIndex:Int,endIndex:Int)
- 지정된 범위 문자를 String 객체로 반환
 ``` kotlin
 ex) 
  stringBuilder = StringBuilder("Hello, World!")
  result = stringBuilder.substring(7,13)
  //stringBuilder : Hello, World!
  //result  : World!
```
 # 결론
문자열로 입력 되어 지고 중간 처리 이후, 출력을 문자열로 할때 자주 쓰이는
클래스이다. 무엇보다 가변이라 시간복잡도를 크게 줄어줘서, 알아두면 좋은
클래스 인것같다.