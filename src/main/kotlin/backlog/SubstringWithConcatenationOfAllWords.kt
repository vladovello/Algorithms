class SubstringWithConcatenationOfAllWords {
    class MutableWordInfo(override var count: Int, index: Int, remains: Int) : WordInfo(index, count, remains)
    open class WordInfo(open var index: Int, open val count: Int, open var remains: Int)

    fun findSubstring(s: String, words: Array<String>): List<Int> {
        val wordsMap = createMapOfWords(words)

        val wordLength = words[0].length
        val permutationLength = wordLength * words.size

        val startingIndices = mutableListOf<Int>()

        for (i in 0 until wordLength) {
            var left = i
            var right = i + permutationLength

            while (right <= s.length) {
                val substr = s.substring(left until right)
                var isGoodSubstring = true

                for (j in substr.indices step wordLength) {
                    val word = substr.substring(j until j + wordLength)

                    if (!wordsMap.containsKey(word)) {
                        left += j + wordLength
                        right = left + permutationLength
                        isGoodSubstring = false
                        break
                    }

                    val wordInfo = wordsMap[word]!!

                    if (isWordAppearedInSubstring(wordInfo, left, left + j, wordLength)) {
                        if (wordInfo.remains == 0) {
                            while (wordInfo.remains == 0) {
                                val leftWord = s.substring(left until left + wordLength)
                                ++wordsMap[leftWord]!!.remains
                                left += wordLength
                                right += wordLength
                            }

                            isGoodSubstring = false
                            break
                        }
                    } else wordInfo.remains = wordInfo.count

                    --wordInfo.remains
                    wordInfo.index = left + j
                }

                if (isGoodSubstring) {
                    startingIndices.add(left)
                    left += wordLength
                    right += wordLength
                }
            }
        }

        return startingIndices
    }

    private fun isWordAppearedInSubstring(
        wordInfo: WordInfo,
        leftEdge: Int,
        currentPosition: Int,
        wordLength: Int
    ): Boolean = wordInfo.index in leftEdge until currentPosition && (wordInfo.index - leftEdge) % wordLength == 0

    private fun createMapOfWords(words: Array<String>): Map<String, WordInfo> {
        val wordsMap: HashMap<String, MutableWordInfo> = hashMapOf()

        for (i in words.indices) {
            wordsMap.merge(words[i], MutableWordInfo(1, -1, 1)) { old, _ ->
                ++old.count
                ++old.remains
                old
            }
        }

        return wordsMap
    }
}