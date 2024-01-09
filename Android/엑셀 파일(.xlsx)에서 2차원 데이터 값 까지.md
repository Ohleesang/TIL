# 엑셀 파일(.xlsx)에서 2차원 데이터 값 까지
### 외부 파일의 데이터값을 내 안드로이드 스튜디오에 넣는법!
<img width="928" alt="dummydata" src="https://github.com/Ohleesang/AppleMarketApp/assets/148442711/c98441cd-6747-41f4-8330-7b9e33a235f2">

> dummy data 를 직접 타이핑 하기 귀찮아...

기존에는 각각 클래스를 구현하고 해당되는 인스턴스값을 직접 타이핑하여 코드를 짰지만,이렇게 액셀파일 형태로 주어진다면 뭔가 고급진 스킬이 있을것같다. 그러한 스킬을 한문장으로 정리하자면,

### InputSteam 으로 파일을 바이트 단위로 읽어서 Apache POI 라이브러리 를 종속받아 액셀값들을 받고 2차원데이터로 변환한다!

이러한 인스턴스에 대한 데이터들을 액셀파일로 주었을때, 타이핑 하지않고 파일을 불러들어 직접 코드에 넣는 방법을 소개 하고자 한다.

### 1. asset 디렉토리 생성
 - app 디렉토리에 New ->Folder -> AssetFolder를 클릭하여 디렉토리를 생성한다.

   <img width="413" alt="directory2" src="https://github.com/Ohleesang/AppleMarketApp/assets/148442711/bed45b40-61cf-49d0-b24a-a8bd4ec9ebf4">

   <img width="413" alt="directory1" src="https://github.com/Ohleesang/AppleMarketApp/assets/148442711/c0cde84f-ef82-4d64-92e2-0d0f5a8e1552">


 - 생생된 asset 폴더에 엑셀파일을 넣는다.


### 2. 종속성 추가
 - Gradle Scirpts 에 들어간다.
    <img width="403" alt="depend" src="https://github.com/Ohleesang/AppleMarketApp/assets/148442711/20d27357-30b5-4e26-ac39-c728dc824c18">
 - depends{ } 에 종속성을 추가한다.
    ```kotlin
    dependencies {
        ...

        //xlsx 파일 종속성 추가
        implementation("org.apache.poi:poi:5.2.3")
        implementation("org.apache.poi:poi-ooxml:5.2.3")
    }
    ```
### 3. asset 폴더에 저장된 excel 파일 열기

```kotlin
fun setDummyData(context: Context, fileName: String) {
        try {
            //A. assets 폴더에 저장된 Excel 파일 열기
            val inputStream: InputStream = context.assets.open(fileName)
            val workbook = XSSFWorkbook(inputStream)
            //java 에서 Excel 파일을 읽고 쓰기위한 Apache POI 라이브러리 클래스
            val sheet = workbook.getSheetAt(0)
            // 첫번째 시트 부분을 불러들이겠다.
            ...
        }
        catch (_: Exception) {
        }
}
```
#### 파라미터
- Context : 해당 액티비티에 저장되어있는 정보들
    > mainActivity일경우 그냥 this 써도 무방!
- fileName : 해당 "파일명.확장자" 인 문자열값

### InputStream??
- 데이터를 바이트 단위로 읽을수 있는 입력 스트림 객체
### 일반적으로 파일을 읽을때 바이트 단위로 읽는다!
그로인해 InputStream 은 바이트스트림을 제공하여 파일 및 네트워크 등 다양한 소스로부터 데이터를 읽을 수 있게해준다!

### 4. 해당 데이터를 값들을 저장

```kotlin
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row

fun setDummyData(context: Context, fileName: String) {
        try {
            ...
           val dataArray = mutableListOf<List<String>>()

            //각 행을 처리
            for (row: Row in sheet) {
                val rowData = mutableListOf<String>()

                // 셀의 개수에 따라 데이터를 배열에 추가
                for (cell in row) {
                    //셀이 비어있으면 무시
                    if (cell.cellType == CellType.BLANK) continue
                    rowData.add(cell.toString())
                }
                if (rowData.isNotEmpty()) dataArray.add(rowData)

            }
            ...
        }
        catch (_: Exception) {
        }
}
```
apache POI 클래스에서 제공해주는 객체 Row,CellType 를 이용하여 dataArray에 값을 저장한다.
### 5. 파일 닫기

```kotlin
    inputStream.close()
```
입력 객체인 InputStream 을 종료한다.

### 이후, 2차원 데이터인 dataArray에 값이 저장된다.
<img width="384" alt="Result" src="https://github.com/Ohleesang/AppleMarketApp/assets/148442711/0f8dec91-4dfa-487e-946e-345e1e9d93f3">

> dataArray[0] 은 액셀 표의 제일 윗부분이니까 생략한다.

## 결론
이번에는 엑셀파일에대한 파일을 읽고 데이터를 저장하는 방식을 정리하였다.이게 어떻게보면 어려울지라도, 나중에 데이터베이스에 값을 받거나 할때도 이러한 흐름으로 코드를 구현하지않을까
1. 바이트단위로 파일을 읽어들이고,
2. 종속받은 해당 라이브러리를 이용하여 데이터를 처리하고,
3. 처리된 데이터로 인스턴스의 값을 넣거나 동적으로 무언가 처리한다.

어떻게 보면 파일을 읽는 부분이지만, 파일을 저장하는 부분도 비슷한 흐름인거 아닐까(직렬화,역직렬화처럼)
어떻게보면 이번에 공부한 개념은 앱프로그래밍을 구현할때 중요한부분이라고 생각이든다.(흔히 외부파일을 읽는경우의 앱은 비일비재하니까)