# RecyclerView(ViewType,ListAdapter)
### 복합적인 RecyclerView 만들어보기!
![homeFragment_slice](https://github.com/Ohleesang/TIL/assets/148442711/40e2260c-ac93-412d-ab28-88a947ec6e3a)

단순한 하나의 RecyclerView 가 아닌, RecyclerView 내에 새로운 RecyclerView 를 구성 하거나, 각각의 Widget 구성하는 복합적인 RecyclerView 를 구현 할 생각이다.

### 사용한 기술
- RecyclerView
  - 리스트 형태로 데이터 형식을 효과적으로 보여주기위해 사용
- ListAdapter
  - 데이터 세트가 변경될 때 매우 효율적으로 업데이트를 처리
- Sealed Class
  - 복합적인 구조를 뷰타입 형식으로 구분 짓기위해서 사용
  - when 표현식을 사용시 따로 else문 을 작성하지 않아도됨

## 구현

### 1. Sealed Class 구성
```kotlin
sealed class HomeWidget {
    //타이틀(카테고리,키워드)
    data class TitleWidget(val title:String): HomeWidget()
    //카테고리 버튼리스트 -> 키워드 버튼리스트 재사용
    data class CategoryWidget(val mCategories : List<Category>) : HomeWidget()
    //카테고리 비디오 결과 리스트
    data class ListCategoryVideoItemWidget(val mUnifiedItems: List<UnifiedItem>) : HomeWidget()
    //키워드 비디오 결과 리스트
    data class ListKeywordVideoItemWidget(val mUnifiedItems: List<UnifiedItem>) : HomeWidget()
}
```
- 화면에 보여질 위젯들을 정리하여 구성
- 중첩된 RecyclerView를 보여주기위해 List형식 으로 데이터를 받는다

### 2. HomeAdapter 구성 및 뷰타입에 따른 바인딩
### A. 뷰타입 정의
```kotlin
//ViewType 정의
private const val TYPE_TITLE = 0
private const val TYPE_CATEGORY = 1
private const val TYPE_LIST_CATEGORY_ITEM_VIDEO = 2
private const val TYPE_LIST_KEYWORD_ITEM_VIDEO = 3


override fun getItemViewType(position: Int): Int {
    return when (getItem(position)) {
        is HomeWidget.TitleWidget -> TYPE_TITLE
        is HomeWidget.CategoryWidget -> TYPE_CATEGORY
        is HomeWidget.ListCategoryVideoItemWidget -> TYPE_LIST_CATEGORY_ITEM_VIDEO
        is HomeWidget.ListKeywordVideoItemWidget -> TYPE_LIST_KEYWORD_ITEM_VIDEO
    }
}
```

### B. 뷰타입에 따른 뷰홀더 정의
- 단일 데이터를 이용한 경우
  ```kotlin
  inner class TitleViewHolder(binding: FragmentHomeTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvTitle = binding.tvTitle
    }
  ```
  - 타이틀 부분(카테고리,키워드) 의 경우 기존 단일데이터 이므로 기존 형식과 같다.
- RecyclerView 를 활용한 경우(리스트형식의 데이터 이용)
  ```kotlin
  inner class CategoryViewHolder(binding: FragmentHomeRvCategoryBtnBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val rvCategory = binding.rvCategory
        val categoryAdapter = CategoryAdapter()

        init {
            rvCategory.layoutManager =
                LinearLayoutManager(rvCategory.context, LinearLayoutManager.HORIZONTAL, false)
            rvCategory.adapter = categoryAdapter
        }
    }
  ```
  - 리스트 형식을 RecyclerView 보여주기위해 클래스내에 RecylcerView 를 연결

### C. 뷰타입에 따른 뷰홀더 생성
```kotlin
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return when (viewType) {
        TYPE_TITLE -> {
            val binding =
                FragmentHomeTitleBinding.inflate(inflater, parent, false)
            return TitleViewHolder(binding)
        }

        TYPE_CATEGORY -> {
            val binding =
                FragmentHomeRvCategoryBtnBinding.inflate(inflater, parent, false)
            return CategoryViewHolder(binding)
        }

        ...

        else -> throw IllegalArgumentException("Invalid view type")
        }
}
  ```
- 뷰타입에 따른 틀(Layout)을 연결하는 부분

### D. 연결된 틀에 주어진 아이템 들을 바인딩
```kotlin
override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    when (val item = getItem(position)) {
        is HomeWidget.TitleWidget -> {
            (holder as TitleViewHolder).tvTitle.text = item.title
        }

        is HomeWidget.CategoryWidget -> {
            (holder as CategoryViewHolder).apply {
                    categoryAdapter.submitList(item.mCategories)
            }
        }

        ...
    }
}
```
- 단일 데이터의 경우, 해당 주여진 틀에 해당 아이템 값을 바인딩
- 리스트 형태의 데이터의 경우,연결한 어뎁터에 데이터를 전달
- 각 RecylerView에 대한 Adapter 도 listAdapter 형식으로 정의(이후, 단일 데이터 처리랑 같음) 


### 3. 메인 리사이클러뷰(rvHomeFragment)에 HomeAdapter(ListAdapter) 연결
```kotlin
override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.rvFragmentHome.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }
```

### 4. 더미데이터 넣고 결과 화면 보기
```kotlin
private fun viewDummyData() {

    var list = mutableListOf<HomeWidget>()
    var categoryList = listOf(Category("1"), Category("10"), Category("21"), Category("31"))
        var dummyVideoItem = UnifiedItem(
            videoTitle = "비디오 이름",
            channelTitle = "채널명",
            description = "",
            dateTime = "20240206",
            thumbnailsUrl = "",
            categoryId = ""
        )
        var videoList = listOf(
            dummyVideoItem, dummyVideoItem, dummyVideoItem, dummyVideoItem, dummyVideoItem
        )
    /**
     *  카테고리 부분
     */

    // 타이틀
    list.add(HomeWidget.TitleWidget("카테고리"))

    // 버튼 리스트
    list.add(HomeWidget.CategoryWidget(categoryList))

    // 비디오 리스트
    list.add(HomeWidget.ListCategoryVideoItemWidget(videoList))

    /**
     *  키워드 부분
     */

    // 타이틀
    list.add(HomeWidget.TitleWidget("키워드"))

    // 버튼 리스트
    list.add(HomeWidget.CategoryWidget(categoryList))

    // 비디오 리스트
    list.add(HomeWidget.ListKeywordVideoItemWidget(videoList + videoList))


    homeAdapter.submitList(list)
    }
```

# 결과

<img width="223" alt="스크린샷 2024-02-06 오후 9 36 07" src="https://github.com/TeamNaTube/NaTube/assets/148442711/7e39077e-e635-4342-948b-03ddd028b004">


## 트러블 슈팅
1. 중첩된 RecyclerView 를 데이터 넣는 방식에 어려움

    a. Sealed Class 로 데이터 클래스를 생성하여,받는 인자값을 리스트 형태 로 저장
    
    b. 해당 RecyclerView를 뷰홀더 클래스에 정의하고 어뎁터를 연결하고 바인딩때 받는 값을 전달 

    -> 결론적으로 ListAdapter 의 구조 및 뷰타입에 대한 이해를 하고 나서 Sealed class 구성하고 해결됨 