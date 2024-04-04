class Array3DColumn<T>(val n:Int, val m:Int, val h : Int, private val arr:Array<T>) {
    init {
        require(n*m*h == arr.size)
    }
    companion object {
        inline fun <reified T> array3d(n:Int, m:Int, h:Int, v:T) =
            Array3DRow<T>(n,m,h,Array<T>(n*m*h){v})

        inline fun <reified T> array3d(n:Int, m:Int, h:Int, block:(Int,Int,Int)->T) =
            Array3DRow<T>(n,m,h, Array<T>(n*m*h){ i ->
                val row = i % (m * h)  // 1차원 array 의 인덱스를 n 인 행의 길이로 나는 나머지
                val col = (i % ( n * m)) / n  // 1차원 array 의 인덱스를 m 인 열의 길이로 나는 몫
                val height = i / (n * m)
                block(row, col, height)
            })
    }

    operator fun get(i: Int, j:Int, h:Int): T = arr[h * n * m + j * n + i]
    operator fun set(i: Int, j:Int, h:Int, v:T): Unit { arr[h * n * m + j * n + i] = v }  // n 은 열
}

fun main() {
    var arr1 = Array3DColumn.array3d(3,3,3, 0.0)
    arr1[0,0,0] = 10.0
    println(arr1[0,0,0])
    arr1[0,1,0] = 12.0
    println(arr1[0,1,0])

    var arr2 = Array3DColumn.array3d(2,3, 3){ i, j, h ->
        "$i,$j,$h"
    }
    arr2[1,2,2] = "test"
    println(arr2[1,2,2])
}