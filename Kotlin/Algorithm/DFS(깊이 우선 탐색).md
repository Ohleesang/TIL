# 깊이 우선 탐색(DFS,Depth-First Search)
### 루트 노트(혹은 다른 임의의 노드)에서 시작해서 
### 다음 분기로 넘어가기전에 해당 분기를 완벽하게 탐색하는방법
![Fig-4-Illustrating-DFS-algorithm](https://github.com/Ohleesang/TIL/assets/148442711/c2931047-3394-4e73-b6f9-5ab8d50bb302)
> A(root)에서 시작해서, 깊이 기준으로 찾은후 돌아가서 J(해법)을 찾는다...! 
>> 이때, 깊이를 우선시 하여 탐색한다는 개념이, 일단 옆 노드보다는 깊게 박아보고 탐색한다!!



## Kotlin 에서 DFS 구현
![graph (2)](https://github.com/Ohleesang/TIL/assets/148442711/be9e5fc9-b2ac-4ad1-ad6d-adff6d88117d)
> 주어진 그래프 노드 형태

- 재귀 함수 이용

>> 4 -> 2 -> 1 -> 3 -> 6 -> 5

```kotlin
fun dfs(graph: Map<Int,List<Int>>,startNode :Int,visited:MutableSet<Int>){
    if(!visited.contains(startNode)){
        println("방문 노드: $startNode")
        visited.add(startNode)


        //인접한 노드들에 대해 재귀 호출
        val neighbors = graph[startNode] ?: emptyList()
        for(neighbor in neighbors){
            dfs(graph,neighbor,visited)
        }
    }
}

fun main(){
    val graph = mapOf(
        1 to listOf(2,3),
        2 to listOf(1,4,5),
        3 to listOf(1,6),
        4 to listOf(2),
        5 to listOf(2),
        6 to listOf(3)
    )
    val startNode = 4
    val visited = mutableSetOf<Int>()          

    dfs(graph,startNode,visited)
}
```
1. dfs 함수는 시작 노드 startNode에서 시작하여 방문한 노드를 출력하고, 해당 노드를 visited에 추가.
2. 시작 노드와 인접한 노드들에 대해 재귀적으로 dfs 함수를 호출.
3. 이미 방문한 노드는 다시 방문하지 않도록 visited 집합을 확인.
- 스택을 이용
> [4] -> [2,5] -> [1],5 - > [3],5 -> [6],5 -> [5]

```kotlin
import java.util.Stack

fun dfs(graph: Map<Int, List<Int>>, startNode: Int) {
    val visited = mutableSetOf<Int>()
    val stack = Stack<Int>()

    stack.push(startNode)

    while (stack.isNotEmpty()) { //스택이 비어있을때까지 반복
        //#1
        val currentNode = stack.pop() 

        //#2
        if (!visited.contains(currentNode)) {
            println("방문 노드: $currentNode")
            visited.add(currentNode)

            //#3
            val neighbors = graph[currentNode] ?: emptyList()
            for (neighbor in neighbors) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor)
                }
            }
        }
    }
}
```
1. 스택에서 노드를 꺼내온다.
2. 해당 노드를 방문하지 않았다면 방문 여부를 체크하고 출력.
3. 인접한 노드들 중에서 아직 방문하지 않은 노드를 스택에 추가.

# 결론 
비록 재귀가 더 간결해보이지만, 스택 기반의 경우, 더 명시적으로 데이터를 추가,제거를 할수 있으므로, 직접적으로 제어가 가능하다. 재귀 방식으로 개념을 이해하고 스택으로 코드를 짜면 될꺼 같다.