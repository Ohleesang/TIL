# Kotlin String Similarty(문자열 유사도) 


## 문자열 유사도??
해당 문자열이 얼마나 다른지 파악하기 위한 계산하는 방법
- Levenshtein 거리
  - 두 문자열사이 의 최소 편집거리를 측정하는 방법
  - 작업 수가 적을수록 두 문자열이 유사하다고 판단
- 코사인 유사도
  - 두 문자열을 벡터로 변환한 후, 이벡터들 사이의 코사인 각도를 측정
  - 0~1의 값
- 자카드 유사도
  - 두 문자열의 집합을 비교하여 유사도를 측정하는 방법
  - 0~1의 값
- Ratcliff/Obershelp 알고리즘
  - 두 문자열 간에 가장 긴 역속된 공통부분 문자열을 찾는것으로 시작
  - 그 다음, 공통부분 문자열을 무시하고 나머지 부분에 대해 동일하게 반복
  - 0~1의 값


해당 알고리즘은 짜기엔 복잡하고 시간이 부족하고...이로 인해 외부 라이브러리를 이용하여 사용 하고자한다.

[라이브러리 Github](https://github.com/tdebatty/java-string-similarity)

## 1. 종속성 추가
위의 링크의 라이브러리는 java 전용 라이브러리이다.

하지만 kotlin도 java 기반이기 때문에 충분히 이용할 수 있다.

 (단, java 8 버젼 이상으로 구현한 라이브러리면 어느정도 제한이있다.)

 ```kotlin
 ...
dependencies{

    implementation("info.debatty:java-string-similarity:2.0.0")
 }
 
 ```
 [build.gradles.kts(:app)]
## 2. 유사성 알고리즘 선택하여 문자열 에 대입
위의 제공해주는 라이브러리는 대략 표로 정리되어있다.

| 알고리즘 | 유형 | 정규화 여부 | 메트릭 여부 | 타입 | 시간 복잡도 | 주로 사용되는 경우 |
|:-------:|:---:|:-------:|:-------:|:---:|:-------:|:-------------:|
|Levenshtein|distance|No|Yes||O(m*n) <sup>1</sup>| |
|Normalized Levenshtein|distance<br>similarity|Yes|No||O(m*n) <sup>1</sup>| |
|Weighted Levenshtein|distance|No|No||O(m*n) <sup>1</sup>|OCR|
|Damerau-Levenshtein <sup>3</sup>|distance|No|Yes||O(m*n) <sup>1</sup>| |
|Optimal String Alignment <sup>3</sup>|distance|No|No||O(m*n) <sup>1</sup>| |
|Jaro-Winkler|similarity<br>distance|Yes|No||O(m*n)|typo correction|
|Longest Common Subsequence|distance|No|No||O(m*n) <sup>1,2</sup>|diff utility, GIT reconciliation|
|Metric Longest Common Subsequence|distance|Yes|Yes||O(m*n) <sup>1,2</sup>| |
|N-gram|distance|Yes|No||O(m*n)| |
|Q-Gram|distance|No|No|Profile|O(m+n)| |
|Cosine similarity|similarity<br>distance|Yes|No|Profile|O(m+n)| |
|Jaccard index|similarity<br>distance|Yes|Yes|Set|O(m+n)| |
|Sorensen-Dice coefficient|similarity<br>distance|Yes|No|Set|O(m+n)| |
|Ratcliff-Obershelp|similarity<br>distance|Yes|No||?| |

구현하고자 하는 기능에 따라 상단의 링크를 참고하여 설명을 자세히 보면 된다.

본인의 경우는 Ratcliff-Obershelp 알고리즘을 이용하여 구현하였다.

```kotlin
import info.debatty.java.stringsimilarity.*

...

val similarityObj = RatcliffObershelp()
        result.forEach { element: Element ->
            val word = element.text
            Log.d("result", word)
            if (word.contains(keyword)) return true
            else if (similarityObj.similarity(word, keyword) >= 0.6) return true
            Log.d("result", similarityObj.similarity(word, keyword).toString())
        }
```
- 인식된 문자가 포함되어 있지 않을경우 문자도 유사도 실행 
- 유사도가 0.6 이상 일경우 같은 문자로 판별


