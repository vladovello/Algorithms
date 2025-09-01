package dfs

class CourseSchedule {

    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val adjacentVertices = Array(numCourses) { mutableListOf<Int>() }
        for (p in prerequisites) adjacentVertices[p[1]].add(p[0])
        val visited = MutableList(numCourses) { 0 }

        fun hasCycle(v: Int): Boolean {
            visited[v] = 1
            for (n in adjacentVertices[v]) {
                if (visited[n] == 2) continue
                if (visited[n] == 1 || hasCycle(n)) return true
            }
            visited[v] = 2
            return false
        }

        for (v in adjacentVertices.indices) {
            if (visited[v] == 0 && hasCycle(v)) return false
        }

        return true
    }
}
