package stack

fun main() {
    val path = "/a/../.././../../."

    println(SimplifyPath().simplifyPath(path))
}

class SimplifyPath {
    fun simplifyPath(path: String): String {
        val folders = CanonicalPathBuilder()
        val pathIterator = AbsolutePathIterator(path)

        while (pathIterator.hasNext()) {
            folders.append(pathIterator.next())
        }

        return folders.build()
    }

    class AbsolutePathIterator(val path: String) : Iterator<String> {
        private var cursor: Int = 0

        override fun hasNext(): Boolean {
            return cursor < path.length
        }

        override fun next(): String {
            while (cursor < path.length && path[cursor] == '/') ++cursor

            val segment = StringBuilder()

            while (cursor < path.length && path[cursor] != '/') {
                segment.append(path[cursor])
                ++cursor
            }

            while (cursor < path.length && path[cursor] == '/') ++cursor

            return segment.toString()
        }
    }

    class CanonicalPathBuilder {
        private val path = ArrayDeque<String>()

        fun append(segment: String) {
            if (segment == "..") {
                if (path.isNotEmpty()) path.removeFirst()
            } else if (segment != ".") path.addFirst(segment)
        }

        fun build(): String {
            val pathSb = StringBuilder()

            while (path.isNotEmpty()) pathSb.append('/' + path.removeLast())

            return if (pathSb.isEmpty()) "/" else pathSb.toString()
        }
    }
}