# OCR SPACE API
[문서 링크](https://ocr.space/ocrapi)
### 이미지 혹은 pdf에서 텍스트를 추출하는 기능을 제공하는 서비스
#### Restful API 으로 제공되며, 기존 Retrofit을 이용하여 데이터를 주고 받을수 있다.

## 무료 플랜 정보
- 가입시 API Key 발급가능
- 월당 25,000건 제한
- 파일크기 제한 : 1MB -> 대략 해상도 1280 & 960 정도(jpeg 기준)

## EndPoint(Post)
```h
https://api.ocr.space/parse/image
```

## Post Parameters

### 필수 파라미터
| Key | Value | 설명 |
| --- | --- | --- |
|apikey |APl Key | 발급받은 API KEY |
|url | url|1. URL 원격 이미지 파일,이미지에 접근할 수있는 웹주소를 제공해야함
|file | multipart 인코딩 방식|2.직접 파일을 보내는 형식
|base64Image| Image or PDF |3.PDF 파일 형식을 보내는 형식
|language|korean=kor|default : eng
- Uri,File,Base64Image 중 택1

### 선택 파라미터
| Key | Value | 설명 |
| --- | --- | --- |
|isOverlayRequired|Boolean| dafult : false, true 일경우 각 인식된 단어에 경계상자의 좌표를 반환
|filetype|String| PDF,GIF,PNG,JPG,TIF,BMP 형식을 overwrites
|detectOrientation|Boolean|true 일경우, 자동으로 회전해준다.
|scale|Boolean|API 내부적으로 업스케일링 해줘서 인식률을 높여줌
|isTable|Boolean|true 일경우 텍스트 결과를 항상 한줄씩으로 반환
|OCREngine|1,2,3| Engines 을 설정가능

## Response
- JSON 형식으로 반환
```json
{
        "ParsedResults" : [
            {
                "TextOverlay" : {
                    "Lines" : [
                        {
                            "Words": [
                                {
                                "WordText": "Word 1",
                                "Left": 106,
                                "Top": 91,
                                "Height": 9,
                                "Width": 11
                                },
                                {
                                "WordText": "Word 2",
                                "Left": 121,
                                "Top": 90,
                                "Height": 13,
                                "Width": 51
                                }
                                .
                                .
                                .
                                More Words
                            ],
                            "MaxHeight": 13,
                            "MinTop": 90
                        },
                        .
                        .
                        .
                        .
                        More Lines
                    ],
                "HasOverlay" : true,
                "Message" : null
                },
                "FileParseExitCode" : "1",
                "ParsedText" : "This is a sample parsed result",
                                        
                "ErrorMessage" : null,
                "ErrorDetails" : null
            },
            {
                "TextOverlay" : null,
                "FileParseExitCode" : -10,
                "ParsedText" : null,
                                        
                "ErrorMessage" : "...error message (if any)",
                "ErrorDetails" : "...detailed error message (if any)"
            }
            .
            .
            .
            ],
        "OCRExitCode" : "2",
        "IsErroredOnProcessing" : false,
        "ErrorMessage" : null,
        "ErrorDetails" : null
        "SearchablePDFURL": "https://....." (if requested, otherwise null) 
        "ProcessingTimeInMilliseconds" : "3000"
    }
```

| Key | Value | 설명 |
| --- | --- | --- |
|ParsedResults|OCR results|인식된 이미지 결과들 반환
|OCRExitCode|integer|인식 결과코드를 반환 
|IsErroredOnProcessing|boolean| 이미지 파싱중 오류가있는가
|ErrorMessage|Text|에러 메세지
|ErrorDetail|Text|자세한 에러 메세지 

### OCRExitCode
1 : 파싱 성공 (Image / All pages parsed successfully)

2: 부분적인 성공 (Only few pages out of all the pages parsed successfully)

3: 파싱 실패 (This happens mainly because the OCR engine fails to parse an image)

4: 파싱 에러(This happens when a fatal error occurs during parsing

### IMAGE / PAGE PARSING RESULT

| Key | Value | 설명 |
| --- | --- | --- |
|FileParseExitCode|Interger|각 결과에따른 EXID 코드|
|ParsedText|Text|파싱된 텍스트|
|TextOverlay|Text in the image|isOverlayRequired = true 일 경우에만 
|Lines|overlayText 내의 라인|리스트형식으로 저장
|Words|line 내의 단어들| 리스트 형식으로 저장
|MaxHeight|integer| Line 중의 가장큰 Height
|MinTop|integer| 좌상점에서 line의 최소거리(px)

### FileParseExitCode
- 0: 파일을 찾을수 없음
- 1: 성공
- -10: 파싱 에러
- -20: 타임 초과
- -30: 유효성 에러
- -99: 알수 없는 에러