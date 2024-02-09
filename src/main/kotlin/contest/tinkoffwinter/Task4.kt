package contest.tinkoffwinter

/*
5 2
A
B
0 1 A
1 2 A
1 2 B
1 1 B
4 2 A
Result:
3

10 3
a
b
c
0 1 a
1 2 a
1 2 b
1 2 c
4 2 a
2 3 a
2 1 b
3 1 c
8 1 a
3 2 c
Result:
6

10 2
a
b
0 1 a
1 2 a
1 2 b
1 2 b
4 2 a
2 2 a
2 1 b
3 1 b
3 1 a
8 1 a
Result:
2
 */

fun main() {
    val (treeSize, companiesNumber) = readlnOrNull()?.split(" ")?.map { it.toInt() }
        ?: throw IllegalArgumentException("First line cannot be null and should contain only numbers")
    val companiesIndexes = readCompanies(companiesNumber)
    val stockTree = StockTree(graphSize = treeSize, companiesIndexes = companiesIndexes)
    println(calculateMinPriceToBuyAllStocks(stockTree))
}

const val INPUT_ROOT_INDEX = -1

private fun calculateMinPriceToBuyAllStocks(stockTree: StockTree): Int {
    var minPrice = Int.MAX_VALUE

    for (i in 0 until stockTree.size) {
        if (!stockTree.containsAllCompanies(i)) continue

        val price = stockTree.getPrice(i)
        if (price < minPrice) {
            minPrice = price
        }
    }

    return minPrice
}

private fun StockTree(graphSize: Int, companiesIndexes: HashMap<String, Int>): StockTree {
    val mutableNodes = mutableListOf<StockTree.Connection>()

    for (i in 0 until graphSize) {
        val (parent, stockNode) = readStockNode(companiesIndexes)
        mutableNodes.add(StockTree.Connection(stockNode, mutableListOf()))

        if (parent == INPUT_ROOT_INDEX) continue

        val parentNode = mutableNodes[parent]
        parentNode.children.add(i)
    }

    return StockTree(mutableNodes, companiesIndexes.size)
}

private class StockTree(private val stocks: MutableList<Connection>, private val companiesNumber: Int) {
    data class Node(val price: Int, val companyIndex: Int, var companiesMask: Int = 1 shl companyIndex)
    data class Connection(val node: Node, val children: MutableList<Int>)

    val size = stocks.size
    private val subtreesPrices = IntArray(stocks.size)

    init {
        calculatePricesAndMasks(0)
    }

    operator fun get(index: Int): Connection = stocks[index]

    fun containsAllCompanies(nodeIndex: Int): Boolean =
        stocks[nodeIndex].node.companiesMask == (1 shl companiesNumber) - 1

    fun getPrice(nodeIndex: Int) = subtreesPrices[nodeIndex]

    private fun calculatePricesAndMasks(nodeIndex: Int) {
        val (node, children) = stocks[nodeIndex]
        subtreesPrices[nodeIndex] += node.price

        for (child in children) {
            calculatePricesAndMasks(child)
            node.companiesMask = node.companiesMask or stocks[child].node.companiesMask
            subtreesPrices[nodeIndex] += subtreesPrices[child]
        }
    }
}

private fun readStockNode(companiesIndexes: HashMap<String, Int>): Pair<Int, StockTree.Node> {
    val (parentNode, stockPrice, companyName) = readlnOrNull()?.split(" ")
        ?: throw IllegalArgumentException("Tree node cannot be null")

    return parentNode.toInt() - 1 to StockTree.Node(
        price = stockPrice.toInt(),
        companyIndex = companiesIndexes[companyName] ?: throw IllegalArgumentException("Company name does not exists")
    )
}

private fun readCompanies(companiesNumber: Int): HashMap<String, Int> {
    val companiesIndexes = hashMapOf<String, Int>()

    for (i in 0 until companiesNumber) {
        val companyName = readlnOrNull() ?: throw IllegalArgumentException("Company name cannot be null")
        companiesIndexes[companyName] = i
    }

    return companiesIndexes
}
