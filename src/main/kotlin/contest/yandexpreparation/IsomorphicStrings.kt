package contest.yandexpreparation

import java.io.BufferedWriter

fun main() {
    val str = Sad().`null`
    println(str)
    println(str.toCharArray())

//    val reader = BufferedReader(System.`in`.reader())
//    val writer = BufferedWriter(System.out.writer())
//
//    val str1 = "str"
//    val str2 = reader.readLine()
//    val str3 = str1.intern()
//
//    writer.writeLine((str1 == str2).toString())
//    writer.writeLine((str1 == str3).toString())
//    writer.writeLine((str2 == str3).toString())
//
//    writer.writeLine("str1 identity hash code: ${System.identityHashCode(str1)}")
//    writer.writeLine("str2 identity hash code: ${System.identityHashCode(str2)}")
//    writer.writeLine("str3 identity hash code: ${System.identityHashCode(str3)}")
//
//    reader.close()
//    writer.close()
}

private fun BufferedWriter.writeLine(str: String) {
    write(str)
    newLine()
}

// 0, null, 1, null, null, null, 2, null, null, null, null, 3

fun isIsomorphic1(s: String, t: String): Boolean {
    val sCharsReferences = mutableMapOf<Char, Char>()
    val tCharsReferences = mutableMapOf<Char, Char>()

    for (i in 0..s.lastIndex) {
        val sContains = sCharsReferences.contains(s[i])
        val tContains = tCharsReferences.contains(t[i])

        if (!sContains && !tContains) {
            sCharsReferences[s[i]] = t[i]
            tCharsReferences[t[i]] = s[i]
        } else if (
            !sContains ||
            !tContains ||
            sCharsReferences[s[i]] != t[i] ||
            tCharsReferences[t[i]] != s[i]
        ) return false
    }

    return true
}

// much faster
fun isIsomorphic2(s: String, t: String): Boolean {
    val sChars = IntArray(128)
    val tChars = IntArray(128)

    for (i in s.indices) {
        if (sChars[s[i].code] != tChars[t[i].code]) return false

        sChars[s[i].code] = i + 1
        tChars[t[i].code] = i + 1
    }

    return true
}
