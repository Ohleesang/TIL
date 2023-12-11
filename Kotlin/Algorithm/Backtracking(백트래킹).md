# 백트래킹(Backtracking) 기법
해를 찾는 알고리즘 기법 중 하나.
해결책의 후보를 검토하다가 그 후보가 해결책이 될수 없다고 판단되면,
### 즉시 이전 단계로 돌아가서 다른 후보를 검토하는 기법
![Backtracking](https://github.com/Ohleesang/TIL/assets/148442711/439bba5a-ee7e-49f9-82c2-8a8b389abb4e)

> 보통 재귀함수를 통하여 구현!!
```kotlin
fun main(){
    //초기 상태 설정
    val initalState = //초기 상태 생성
    val path = mutableListOf<State>()

    //함수 호출
    backtrack(initalState,path)
}

fun backtrack(currentState : State,path: MutableList<State>){
    //종료 조건 확인
    if(isGoal(currentState){
        //목표의 도달한 경우 : 결과 처리
        processSolution(path)
        return
    })

    //다음 후보들을 생성하고 검토
    val candidates = generateCandidates(currentState)
    for (condidate in candidates){
        //후보를 시도
        path.add(candidate)

        //다음단계 이동
        backtrack(candidate,path)

        //백트래킹 : 후보가 유효하지 않다고 판단되면 이전 상태로 돌아감
        path.removeAt(path.size-1)
    }
}

fun isGoal(state: State):Boolean 
// 현재 상태가 목표에 도달했는가

fun generateCandidates(state :State):List<State>
// 현재 상태에서 다음 후보들을 생성하고 반환

fun processSolution(soulutionPath : List<State>) 
//찾은 해결책에 대한 처리

```

## 예제 : N-queen 문제

N x N 체스판에 N개의 퀸을 서로 공격할수 없도록 배치하여라.
> 퀸의 경우, 상하좌우 대각선으로 움직일 수 있다.
![queen-attack-range](https://github.com/Ohleesang/TIL/assets/148442711/6fd8f104-39b3-4a48-a26e-92f45d3aca83)
    - n =8 일 경우..
![nqueen-solve](https://github.com/Ohleesang/TIL/assets/148442711/d419bbdd-d624-431e-acc9-8b1ee79b06d0)


```kotlin
fun solveNQueens(n: Int): List<List<String>> {
    // 문자가 들어간 2차원 배열을 반환
    val result = mutableListOf<List<String>>()
    val board = Array(n) { CharArray(n) { '.' } } // N x N 배열 '.' 초기화
    placeQueens(0, board, result)

    return result
}

fun placeQueens(row: Int, board: Array<CharArray>, result: MutableList<List<String>>) {

    //#2 현재 배치가 유효한 경우 결과에 추가 (재귀함수의 도착점)
    if (row == board.size) {
        result.add(board.map { it.joinToString("") })
        return
    }


    // #1 퀸을 놓을 수 있는지 확인하고 백트래킹을 시도!
    for (col in board.indices) {
        if (isValid(row, col, board)) {
            board[row][col] = 'Q'  // 퀸을 현재 위치에 놓음
            placeQueens(row + 1, board, result)  // 다음 행으로 이동
            board[row][col] = '.'  // 백트래킹: 퀸을 제거하여 다른 배치 시도
        }
        //즉, 끝까지 도착하여 해답을 찾는다면, 백트래킹을 시행되지않고 #2의 결과로 도달한다.
    }

}

// 퀸을 놓을수 있는가 board 값에서 확인!(위에서 밑으로 내려가므로, 윗부분만 확인!)
fun isValid(row: Int, col: Int, board: Array<CharArray>): Boolean {
    // 같은 열에 퀸이 있는지 확인
    for (i in 0 until row) {
        if (board[i][col] == 'Q') {
            return false
        }
    }

    // 왼쪽 위 대각선에 퀸이 있는지 확인
    var i = row - 1
    var j = col - 1
    while (i >= 0 && j >= 0) {
        if (board[i][j] == 'Q') {
            return false
        }
        i--
        j--
    }

    // 오른쪽 위 대각선에 퀸이 있는지 확인
    i = row - 1
    j = col + 1
    while (i >= 0 && j < board.size) {
        if (board[i][j] == 'Q') {
            return false
        }
        i--
        j++
    }

    return true
}

fun main() {
    // 크기가 4인 경우
    val n = 4
    val solutions = solveNQueens(n)
    for (solution in solutions) {
        println(solution)
    }
}
```
- placeQueens 라는 재귀함수를 이용하여 모든 경우를 탐색한다.
#### 즉, 끝까지 도착하여 해답을 찾는다면, 백트래킹이 시행되지않고 #2의 결과로 도달한다.
> 만약 끝까지 도착한다면, board[i][j]='.' 같은 초기화 하는코드를 시행 할 수 가없다.

# 결론
백트래킹(Backtracking) 기법이 다양한 문제에 적용될 수 있는 강력한 기법이지만,조건을 타면
오히려 시간복잡도가 높을수 있는 가능성이 있어서 최선의 방식은 아닌것같다. 하지만 이러한 개념을 알고있으면 특성에 따라, 효율적인 알고리즘을 짤 수 있을것 같다.