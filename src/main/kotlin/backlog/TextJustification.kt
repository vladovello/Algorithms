internal class TextJustification {
    fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
        val justifiedText = mutableListOf<String>()
        val row = mutableListOf<String>()
        var rowContentLen = 0

        for (word in words) {
            if (rowContentLen + row.size + word.length > maxWidth) {
                justifiedText.add(alignSpacesInRow(row, rowContentLen, maxWidth))
                row.clear()
                rowContentLen = 0
            }

            row.add(word)
            rowContentLen += word.length
        }


        if (row.size > 0) {
//            val lastLine = row.foldIndexed(StringBuilder()) { i, acc, s ->
//                acc.append(s)
//                if (i != row.lastIndex) acc.append(" ")
//                else acc.append(" ".repeat(maxWidth - rowContentLen - row.size + 1))
//                acc
//            }

            val lastLine = StringBuilder()

            repeat(row.size - 1) {
                lastLine.append(row[it])
                lastLine.append(" ")
            }

            lastLine.append(row.last())
            lastLine.append(" ".repeat(maxWidth - rowContentLen - row.size + 1))

            justifiedText.add(
                lastLine.toString()
            )
        }

        return justifiedText
    }

    private fun alignSpacesInRow(row: List<String>, rowContentLen: Int, maxWidth: Int): String {
        val alignedRow = StringBuilder()
        var remainingSpace = maxWidth - rowContentLen
        var gapsCount = if (row.size == 1) 1 else row.size - 1

        for (word in row) {
            alignedRow.append(word)

            if (gapsCount == 0) break

            val spacesNumber = remainingSpace / gapsCount + if (remainingSpace % gapsCount == 0) 0 else 1
            alignedRow.append(" ".repeat(spacesNumber))

            remainingSpace -= spacesNumber
            --gapsCount
        }

        return alignedRow.toString()
    }
}