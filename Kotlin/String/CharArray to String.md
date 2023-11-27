# CharArray to String

배열을 쓰다보면, 각 문자를 처리한 후,
다시 String 으로 치환할때 원하는 대로 안되는 경우가 있다.
이때 사용하는 방식으로 총 3가지가 있다.
```kotlin
fun main(args: Array<String>) {
    var str = "abcde"
    var charArr = str.toCharArray()
    var out_str = charArr.toString()
    println(str)
    println(charArr)
    println(out_str)
}
/**
 * abcde
 * abcde
 * [C@9807454 ...주소값이 반환?? 
 * 
 */
```
1.StringBuilder() 함수 이용하기
``` kotlin
out_str = StringBuilder().append(charArr).toString() // abcde
```
2.joinToString() 함수 이용하기
``` kotlin
out_str = charArr.joinToString() //a, b, c, d, e
out_str = out_str.replace(", ","") // abcde
```
3.String 생성자 이용
``` kotlin
out_str =String(charArray)// abcde
```
# 결론

3번의 경우가 가장 쉬운방법이지만, 막상 배열값이 문자가 아닌경우도 고려할때가있다.
이때, 3번은 적용되지않으므로 1,2번도 알아두는게 좋다고 생각한다.