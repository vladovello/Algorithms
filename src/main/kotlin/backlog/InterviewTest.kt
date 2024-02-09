interface Source<out T> {
    fun nextT(): T
}

fun demoOut(strings: Source<Number>) {
    val objects: Source<Any> = strings // This is OK, since T is an out-parameter
}

fun demoIn(x: Comparable<Number>) {
    x.compareTo(2.0) // 1.0 has type Double, which is a subtype of Number
    // Thus, you can assign x to a variable of type Comparable<Double>
    val y: Comparable<Double> = x
}