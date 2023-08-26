package hashmap

fun main() {
    val s = "paper"
    val t = "title"

    println(IsomorphicStrings().isIsomorphic(s, t))
}

class IsomorphicStrings {
    fun isIsomorphic(s: String, t: String): Boolean {
        val sSymbolsOrder = hashMapOf<Char, Int>()
        val tSymbolsOrder = hashMapOf<Char, Int>()

        for (i in s.indices) {
            val sSymbolOrder = sSymbolsOrder.getOrDefault(s[i], i)
            val tSymbolOrder = tSymbolsOrder.getOrDefault(t[i], i)

            if (sSymbolOrder != tSymbolOrder) return false

            sSymbolsOrder[s[i]] = sSymbolOrder
            tSymbolsOrder[t[i]] = tSymbolOrder
        }

        return true
    }
}