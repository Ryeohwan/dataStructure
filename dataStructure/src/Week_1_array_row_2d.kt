class Array2DRow<T>(val n:Int, val m:Int, private val arr:Array<T>) {
    init {
        require(n*m == arr.size)
    }
    companion object {
        inline fun <reified T> array2d(n:Int, m:Int, v:T) =
            Array2DRow<T>(n,m,Array<T>(n*m){v})

        inline fun <reified T> array2d(n:Int, m:Int, block:(Int,Int)->T) =
            Array2DRow<T>(n,m,Array<T>(n*m){ i ->
                val row = i / n  // 1차원 array 의 인덱스를 n 인 행의 길이로 나는 몫
                val col = i % m  // 1차원 array 의 인덱스를 m 인 열의 길이로 나는 나머지
                block(row,col)
            })
    }

    operator fun get(i: Int, j:Int): T = arr[j * n + i]
    operator fun set(i: Int, j:Int, v:T): Unit { arr[j * n + i] = v }  // n 은 행
}

fun main() {
    var arr1 = Array2DRow.array2d(3,3,0.0)
    arr1[0,0] = 10.0
    println(arr1[0,0])

    var arr2 = Array2DRow.array2d(2,3){ i, j ->
        "$i,$j"
    }
    arr2[1,2] = "test"
    println(arr2[1,2])
}