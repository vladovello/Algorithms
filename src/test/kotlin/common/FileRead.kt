package common

import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.Objects

fun <T> readResourceFileByLine(fileName: String?, read: (String) -> T): List<T> {
    val classLoader = object {}::class.java.classLoader

    try {
        classLoader.getResourceAsStream(fileName).use { inputStream ->
            InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8).use { reader ->
                BufferedReader(reader).use { bufferedReader ->
                    val result = mutableListOf<T>()
                    var line: String? = bufferedReader.readLine()
                    while (line != null) {
                        result.add(read(line))
                        line = bufferedReader.readLine()
                    }

                    return result
                }
            }
        }
    } catch (e: Exception) {
        System.err.println("Error reading resource file: " + e.message)
    }

    return emptyList()
}
