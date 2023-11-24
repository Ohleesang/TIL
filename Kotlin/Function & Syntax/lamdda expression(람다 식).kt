# 람다 식?
- 람다 식(lambda expression)은 이름이 없는 함수,함수를 간결하게 표현할 수 있는
방법 중 하나. {}로 둘러싸인 코드 블록으로 표현 되고, '->' 기호가 사용된다.
보통 고차함수(higher-order function)와 함께 많이 사용된다.(ex associate 함수)

{매개변수 -> 함수 본문}

예제1)
val sum : (Int,Int) -> Int = {a,b -> a+b}

@ sum은 (Int,Int)->Int 형식의 변수로 선언되었다. {정수 두개를 "매개변수"
-> "덧셈"} 을 수행하고 결과를 반환한다.

예제2)
val numbers = listOf(1, 2, 3, 4, 5)
numbers.forEach { println(it) }

@ 'forEach'는 리스트의 각 요소에 대해 주어진 람다식을 실행.
{ it -> println(it) } 각 요소를 출력

# 결론
람다 식은 코드를 간결하게 만들어주고, 함수형 프로그래밍의 기능을 제공하여
익숙해지면 코딩에 편해질꺼 같다.

