import kotlin.math.abs
data class Point(val x: Double, val y: Double)
data class Triangle(val vertex1: Point, val vertex2: Point, val vertex3: Point)
fun main() {
    val triangle = readTriangle()
    val point = readPoint()

    val isInsideTriangle = isPointInsideTriangle(point, triangle)

    println(if (isInsideTriangle) "Точка находится внутри треугольника." else "Точка находится вне треугольника.")
}

private fun readTriangle(): Triangle {
    println("Введите координаты вершин треугольника:")

    val vertex1 = readPoint("Вершина 1")
    val vertex2 = readPoint("Вершина 2")
    val vertex3 = readPoint("Вершина 3")

    return Triangle(vertex1, vertex2, vertex3)
}

private fun readPoint(prompt: String = "Точка"): Point {
    while (true) {
        print("$prompt (x y): ")
        val input = readLine()?.split(" ")

        if (input != null && input.size == 2) {
            val (x, y) = input.map { it.toDoubleOrNull() }
            if (x != null && y != null) {
                return Point(x, y)
            }
        }

        println("Некорректный ввод. Попробуйте еще раз.")
    }
}

private fun isPointInsideTriangle(point: Point, triangle: Triangle): Boolean {
    val area1 = calculateTriangleArea(point, triangle.vertex2, triangle.vertex3)
    val area2 = calculateTriangleArea(point, triangle.vertex1, triangle.vertex3)
    val area3 = calculateTriangleArea(point, triangle.vertex1, triangle.vertex2)
    val totalArea = calculateTriangleArea(triangle.vertex1, triangle.vertex2, triangle.vertex3)

    return abs(totalArea - (area1 + area2 + area3)) < 0.0001
}

private fun calculateTriangleArea(vertex1: Point, vertex2: Point, vertex3: Point): Double {
    return abs((vertex1.x * (vertex2.y - vertex3.y) + vertex2.x * (vertex3.y - vertex1.y) + vertex3.x * (vertex1.y - vertex2.y)) / 2)
}
