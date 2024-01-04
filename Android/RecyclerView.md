# RecyclerView

### 리스트형태의 Data를 표시하는데 사용되는 위젯중 최신 방식!

![4h2wjwo0dlzkenw9ewnm](https://github.com/Ohleesang/AppleMarketApp/assets/148442711/6efb7847-9e2c-42fb-914a-10e0e8493354)

> 리스트를 스크롤 할때, 자원들을 재사용하는 방식!(ListView 는 그이전에 쓰이던 방식)

### RecyclerView는 화면에 보이는 항목만을 랜더링하고 나머지는 재활용 하여 자원 효율성을 높이는 List를 보여주는 위젯이다!

## ListView vs RecyclerView
| 특성| RecyclerView|ListView|
|--------------------------|-----------------------------------------|------------------------------------------|
| 유형                      | 안드로이드 5.0 (롤리팝) 이상에서 도입        | 안드로이드 1.0부터 사용 가능                  |
| 레이아웃 매니저(Layout Manager) | 필수, 다양한 레이아웃 매니저를 지원            | 기본적으로 세로 스크롤을 위한 LinearLayout 사용         |
| ViewHolder 패턴 사용        | 필수, 데이터 아이템의 뷰를 재활용하여 성능 향상   | 권장, 하지만 강제는 아님                          |
| 어댑터(Adapter)          | RecyclerView.Adapter를 상속받아 구현           | BaseAdapter 또는 ArrayAdapter를 상속받아 구현      |
| 갱신 및 삭제 애니메이션     | 기본적으로 지원                             | 일반적으로 지원하지 않음                          |
| ViewType 지원             | 다양한 뷰 유형을 지원하여 다양한 레이아웃 구현 가능 | 단일 레이아웃만 지원                              |

결론적으로 RecyclerView 가 더 최신이고 유연한 기능을 제공하여 특히 ViewHolder 패턴을 강제하므로 
### RecyclerView를 사용하는걸 권장한다.

## RecyclerView 사용 
![ppua0yojv1shl6jfjfnm ](https://github.com/Ohleesang/AppleMarketApp/assets/148442711/c2a733a7-7c17-4dfd-829c-ecfa5e42ebc7)
다음, 안드로이드에서 RecyclerView 위젯을 사용하기위해 세가지 준비물이 필요하다.
- LayoutManager
- Adapter
- ViewHolder

### LayoutManager
### RecycleView 의 데이터를 어떤 형태로 화면에 표시할지 관리하는 클래스
- LinearLayoutManager : 간단한 선형레이아웃
- GridLayoutManager : 격자 형태의 그리드레이아웃
- StaggeredGridLayoutManager : 격자지만 각 항목의 크기가 다를떄 사용


![layoutManager](https://github.com/Ohleesang/TIL/assets/148442711/3ee5c899-390a-4721-9fc1-b6c9cc3d2d77)
#### 예제
```kotlin
        ...

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        //리니어레이아웃으로 형태를 지정해준다!
```
### Adapter
안드로이드 UI와 데이터 사이의 다리 역할을 하는 클래스

![RecyclerView](https://github.com/Ohleesang/TIL/assets/148442711/46fc5a1f-1cec-4936-b50c-c047e645b7cb)

### RecyclerView에서는 ViewHolder(보여지는부분) 과 데이터를 연결시켜주는 징검다리역할을 해준다.

#### 예제
```kotlin
class MyAdapter(private val mItems: MutableList<MyItem>) :
    RecyclerView.Adapter<MyAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    lateinit var itemClick: ItemClick

    //새로운 아이템뷰홀더가 필요할 떄 호출 즉, 최초생성시만 호출
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        //parent : recyclerView의 객체
        val binding =
            ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //RecyclerView의 각 아이템에 대해 인플레이트된 레이아웃을 동적으로 추가
        return Holder(binding)
    }

    //특정위치에서 데이터를 바인딩할때 호출, 뷰 홀더와 데이터를 연결
    //각 아이템이 화면에 나타날때마다 호출
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it,position)
        }
        holder.iconImageView.setImageResource(mItems[position].icon)
        holder.name.text = mItems[position].name
        holder.age.text = mItems[position].age
    }


    override fun getItemCount(): Int {
        return mItems.size
    }
    ...
}

```
```kotlin
        //어뎁터 생성
        val adapter = MyAdapter(dataList)

        //RecyclerView에 어뎁터 연결
        binding.recyclerView.adapter = adapter //어뎁터 연결!
```
### ViewHolder
### 화면에 표시될 데이터나 아이템들을 저장하는 객체!

즉, 뷰의 재사용을 돕는객체!

하나의 뷰를 나타내는 레이아웃 계층구조를 보관하고, 이를 재사용할수 있도록함.

```kotlin
class MyAdapter(private val mItems: MutableList<MyItem>) :
    RecyclerView.Adapter<MyAdapter.Holder>() {
        ...

    inner class Holder(binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val iconImageView = binding.iconItem
        val name = binding.textItem1
        val age = binding.textItem2
        }
    }
```

## 결론
기존의 경우 scrollview를 이용하여 리스트뷰들을 처리했는데 무언가 RecyclerView라는걸 보니까 내가 정말 코드를 원시적으로 했다고 느껴졌다.비록 어려운 개념이지만 막상 이해해놓고보니 정말 클래스를 잘 활용한다는 느낌이 강했다. TIL를 작성했지만 실제로 많이 쓰이는 위젯이므로 다시 몇번이나 정독해야할 개념인것같다.