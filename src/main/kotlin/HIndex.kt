internal class HIndex {
    fun hIndex(citations: IntArray): Int {
        val paperEntries = hashMapOf<Int, Int>()
        var h = 0

        for (paperCitations in citations) {
            if (paperCitations >= h + 1) {
                if (paperEntries.containsKey(h)) {
                    if (paperEntries[h] == 1) paperEntries.remove(h) else paperEntries[h] = paperEntries[h]!! - 1
                    --h
                }

                paperEntries.merge(paperCitations, 1, Int::plus)
                ++h
            }
        }

        return h
    }
}