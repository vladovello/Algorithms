package hashmap

fun main() {
    val strs = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
    GroupAnagrams().groupAnagrams(strs)
}

class GroupAnagrams {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val groupedAnagrams = mutableListOf<MutableList<String>>()
        val sortedWordToGroup = hashMapOf<String, MutableList<String>>()

        for (s in strs) {
            val sortedStr = s.toCharArray().sorted().joinToString("")
            val group = sortedWordToGroup.getOrDefault(sortedStr, mutableListOf())

            

            group.add(s)
        }

        return groupedAnagrams
    }
}