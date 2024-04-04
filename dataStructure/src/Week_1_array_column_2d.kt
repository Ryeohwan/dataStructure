class Array2DColumn<T>(val n:Int, val m:Int, private val arr:Array<T>) {
    init {
        require(n*m == arr.size)
    }
    companion object {
        inline fun <reified T> array2d(n:Int, m:Int, v:T) =
            Array2DColumn<T>(n,m,Array<T>(n*m){v})

        inline fun <reified T> array2d(n:Int, m:Int, block:(Int,Int)->T) =
            Array2DColumn<T>(n,m,Array<T>(n*m){ i ->
                val row = i % n  // 1차원 array 의 인덱스를 n 인 행의 길이로 나는 나머지
                val col = i / m  // 1차원 array 의 인덱스를 m 인 열의 길이로 나는 몫
                block(row,col)
            })
    }

    operator fun get(i: Int, j:Int): T = arr[i * n + j]
    operator fun set(i: Int, j:Int, v:T): Unit { arr[i * n + j] = v }  // n 은 열
}

fun main() {
    var arr1 = Array2DColumn.array2d(3,3,0.0)
    arr1[0,0] = 10.0
    println(arr1[0,0])

    var arr2 = Array2DColumn.array2d(2,3){ i,j ->
        "$i,$j"
    }
    arr2[1,2] = "test"
    println(arr2[1,2])
}