package backlog

import java.time.Instant
import java.util.PriorityQueue as PQ

typealias Key = Int
typealias Value = Int

interface LRUCache {

    fun get(key: Key): Int
    fun put(key: Key, value: Value)
}

object LRUCacheSolutions {

    class Solution1(val capacity: Int) : LRUCache {

        private val cacheEntries: MutableMap<Key, Value> = mutableMapOf()
        private val keysByPriorities = PQ<LRUCacheEntry>(capacity) { p1, p2 -> p1.timestamp.compareTo(p2.timestamp) }

        class LRUCacheEntry(val key: Key, val timestamp: Long) {

            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as LRUCacheEntry

                return key == other.key
            }

            override fun hashCode(): Int {
                return key
            }
        }

        override fun get(key: Key): Int {
            val value = cacheEntries[key] ?: return -1
            val cacheEntry = createCacheEntry(key)
            keysByPriorities.remove(cacheEntry)
            keysByPriorities.offer(cacheEntry)
            return value
        }

        override fun put(key: Key, value: Value) {
            val cacheEntry = createCacheEntry(key)

            if (cacheEntries.containsKey(key)) {
                keysByPriorities.remove(cacheEntry)
            } else if (keysByPriorities.size == capacity) {
                cacheEntries.remove(keysByPriorities.poll().key)
            }

            keysByPriorities.offer(cacheEntry)
            cacheEntries[key] = value
        }

        private fun createCacheEntry(key: Key): LRUCacheEntry =
            LRUCacheEntry(key, Instant.now().let { it.epochSecond * 1_000_000_000L + it.nano })
    }

    class Solution2(val capacity: Int) : LRUCache {
        private class Node(val key: Int, var value: Int, var next: Node? = null, var prev: Node? = null)

        private val cacheEntries = mutableMapOf<Int, Node>()
        private var head: Node? = null
        private var tail: Node? = null

        override fun get(key: Key): Int {
            val node = cacheEntries[key] ?: return -1
            node.removeFromList().moveToHead()
            return node.value
        }

        override fun put(key: Key, value: Value) {
            val node = cacheEntries[key]
            if (node != null) {
                node.removeFromList().moveToHead().value = value
                return
            }

            if (cacheEntries.size == capacity) {
                cacheEntries.remove(tail?.key)
                tail?.removeFromList()
            }

            cacheEntries[key] = Node(key, value).moveToHead()
        }

        private fun Node.removeFromList(): Node {
            this.prev?.next = this.next
            this.next?.prev = this.prev
            when {
                this === tail -> tail = this.next
                this === head -> head = this.prev
            }
            return this
        }

        private fun Node.moveToHead(): Node {
            head?.next = this
            this.prev = head
            this.next = null
            head = this
            if (tail == null) tail = head
            return this
        }
    }
}
