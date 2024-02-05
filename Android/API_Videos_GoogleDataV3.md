# Videos(googleDataApiV3)
[API 참고 링크](https://developers.google.com/youtube/v3/docs/videos?hl=ko)

## Videos : list

- 할당량 : (1/10000)

### 사용 사례
- list(by video ID)
  - 특정 비디오에 대한 정보를 검색
  - 비디오를 식별하기위해 id 매개변수 이용
- list(multiple video IDs)
  - 비디오 그룹에 대한 정보를 검색
  - id가 여러개를 쉼표로 구분
  - 재생 목록의 항목 또는 검색 쿼리의 결과에 대한 추가 정보를 검색
- list(most popular videos)
  - Youtube 에 가장 인기있는 목록 을 검색
  - regionCode : 비디오를 검색한 국가(기본은 미국)
  - VideoCategoryId : 특정 범주(카테고리) 에서 가장 인기있는 비디오를 검색 가능
- list(my liked videos)
  - API 요청을 승인하는 사용자가 좋아하는 비디오 목록을 검색
  - 또한 등급 매개 변수값을 비호감으로 설정하여 이코드를 사용하여 비호감 비디오를 검색 가능


## 요청
- http 요청
```http
GET https://www.googleapis.com/youtube/v3/videos
```
### 필수 매개변수

### part : String
  - API 응답이 포함하는 video 리소스 속성 하나 이상의 쉼표로 구분된 목록을 지정
  - snippet : channelId,title,description,tags,categoryId 속성이 포함되어있음

이외의 값들을 추가해서 넣을수 있다.

|변수|	설명|
|---|-----|
|contentDetails|	동영상의 콘텐츠 관련 세부 정보
|fileDetails|	동영상 파일의 세부 정보
|id|	동영상의 고유 식별자
|liveStreamingDetails|	동영상의 실시간 스트리밍 관련 정보
|localizations|	동영상의 지역화 정보
|player|	동영상 재생에 사용되는 플레이어 정보
|processingDetails|	동영상 처리 관련 세부 정보
|recordingDetails|	동영상의 녹화 관련 정보
|snippet|	동영상의 기본 정보
|statistics|	동영상의 통계 정보
|status|	동영상의 상태 정보
|suggestions|	동영상 관련 제안 정보
|topicDetails|	동영상의 주제 관련 정보

### 필터(다음 매개변수중 필수 하나 선택)
- chart
  - 검색할려는 차트를 식별
  - mostpopular : 지정된 콘텐츠 지역 및 동영상 카테고리에 대해 가장 인기는 동영상을 반환
- id
  - 동영상의 id
- myRating
  - 인증된 요청만 사용가능
  - 사용자가 선택한 좋아요와 싫어요만 반환

#### 선택적 매개변수
필수 사항이 아닌 매개변수들

- maxResults : 결과 집합에 반환되는 최대 항목 수(기본값 :5,1~50)
- maxHeight,maxWidth : 최대 높이와 최대 너비를 지정 할수 있음(72~8192)
- regionCode : 지정된 지역에 제공되는 동영상 차트를 선택(한국 : KR)
- videoCategoryId : 차트를 검색해야하는 동영상 카테코리를 식별, **chart 매개변수** 와만 함께 사용할 수 있다.

## 요약
```kotlin
@Query("part") part: String = "snippet",
@Query("maxResults") maxResults: Int = API_MAX_RESULT,
@Query("regionCode") regionCode: String = API_REGION,
@Query("key") apiKey: String = YOUTUBE_API_KEY,
@Query("chart") chart : String = "mostPopular"
```

## 응답
```json
{
  "kind": "youtube#videoListResponse",
  "etag": etag,
  "nextPageToken": string,
  "prevPageToken": string,
  "pageInfo": {
    "totalResults": integer,
    "resultsPerPage": integer
  },
  "items": [
    video Resource
  ]
}
```

|필드|	설명|
|---|-----|
|kind|	API 리소스의 유형을 식별 값(youtube#videoListResponse)
|etag|	Entity Tag,리소스가 변경되었는지 확인
|nextPageToken|	다음 페이지를 가져오기 위해 pageToken 매개변수의 값으로 사용
|prevPageToken|	이전 페이지를 검색하기 위해 pageToken 매개변수의 값으로 사용
|pageInfo|	결과 세트의 페이징 정보를 캡슐화하는 pageInfo 객체
|pageInfo.totalResults|	결과 집합의 총 결과 수
|pageInfo.resultsPerPage|	API 응답에 포함된 결과 수
|items[]| 요청 기준과 일치하는 동영상 목록

### items[]
```kotlin
data class Item(
    val etag: String, // Entity Tag
    val id: String, // Video 고유한 값
    val kind: String, // youtube#video
    val snippet: Snippet, // video에 대한 기본 정보들
)
```
### snippet
```kotlin
data class Snippet(
    val categoryId: String, // 카테고리 구분
    val channelId: String, // 채널 ID
    val channelTitle: String,// 채널 이름
    val defaultAudioLanguage: String,
    val defaultLanguage: String,
    val description: String,// 채널 설명
    val liveBroadcastContent: String,
    val localized: Localized,
    val publishedAt: String,// 채널 등록일
    val tags: List<String>,// 채널 태그 목록
    val thumbnails: Thumbnails,// 썸네일 사진들
    val title: String, // 해당 동영상 이름
)
```

## 요약
- 해당 snippet 의 정보의 일부를 받아들어서, 새로운 데이터 클래스로 정의해야함