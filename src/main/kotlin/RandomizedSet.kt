class RandomizedSet {
    private val hashMap = hashMapOf<Int, Any?>()

    fun insert(`val`: Int): Boolean {
        if (hashMap.containsKey(`val`)) return false

        hashMap[`val`] = null
        return true
    }

    fun remove(`val`: Int): Boolean {
        if (!hashMap.containsKey(`val`)) return false

        hashMap.remove(`val`)
        return true
    }

    fun getRandom(): Int {
        return hashMap.keys.random()
    }
}