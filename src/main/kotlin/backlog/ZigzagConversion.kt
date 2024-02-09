fun main() {
    val s = "A"
    println(ZigzagConversion().convert(s, 1))
}

class ZigzagConversion {
    fun convert(s: String, numRows: Int): String {
        if (numRows == 1) return s

        val zigzagString = StringBuilder()

        for (currentRow in 1..numRows) {
            val toZigzagMargin = (numRows - currentRow) * 2
            val toRowMargin = (currentRow - 1) * 2

            val margin = if (toZigzagMargin != 0) toZigzagMargin else toRowMargin
            val alignmentMargin = if (toRowMargin != 0) toRowMargin else toZigzagMargin

            var i = currentRow - 1
            while (i < s.length) {
                zigzagString.append(s[i])
                i += margin

                if (i >= s.length) break

                zigzagString.append(s[i])
                i += alignmentMargin
            }
        }

        return zigzagString.toString()
    }
}