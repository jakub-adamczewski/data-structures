import kotlin.math.max

class BinarySearchTree : Structure() {

    class BSTNode(val data: Int, var left: BSTNode? = null, var right: BSTNode? = null) : Node()

    private var root: BSTNode? = null

    override fun insert(value: Int) {
        root = insertRec(root, value);
    }

    private fun insertRec(currentRoot: BSTNode?, key: Int): BSTNode {

        /* If the tree is empty, return a new node */
        if (currentRoot == null) {
            return BSTNode(key);
        }

        /* Otherwise, recur down the tree */
        if (key < currentRoot.data)
            currentRoot.left = insertRec(currentRoot.left, key);
        else if (key > currentRoot.data)
            currentRoot.right = insertRec(currentRoot.right, key);

        /* return the (unchanged) node pointer */
        return currentRoot;
    }


    override fun remove() {
        removeTree(root)
        root = null
    }

    private fun removeTree(currentRoot: BSTNode?) {
        if (currentRoot == null) return

        removeTree(currentRoot.left)
        removeTree(currentRoot.right)

        currentRoot.left = null
        currentRoot.right = null
    }

    override fun findByValue(value: Int): BSTNode? {
        var currentNode: BSTNode? = root

        while (currentNode != null) {
            if (value == currentNode.data) return currentNode
            else if (value < currentNode.data) currentNode = currentNode.left
            else currentNode = currentNode.right
        }

        return null
    }

    fun print() {
        printBstTitle()
        printStart()
        inOrderRec(root)
        printEnd()
    }

    private fun inOrderRec(currentRoot: BSTNode?) {
        if (currentRoot != null) {
            inOrderRec(currentRoot.left)
            print(" ${currentRoot.data},")
            inOrderRec(currentRoot.right)
        }
    }

    fun getHeight(currentRoot: BSTNode? = root): Int {
        if (currentRoot == null) return -1
        val leftHeight = getHeight(currentRoot.left)
        val rightHeight = getHeight(currentRoot.right)
        return max(leftHeight, rightHeight) + 1
    }

    private fun storeBstNodes(currentRoot: BSTNode?, nodes: MutableList<BSTNode>) {
        if (currentRoot == null) return

        storeBstNodes(currentRoot.left, nodes)
        nodes.add(currentRoot)
        storeBstNodes(currentRoot.right, nodes)
    }

    private fun buildTreeUtil(nodes: List<BSTNode>, start: Int, end: Int): BSTNode? {
        if (start > end) return null

        val mid = (start + end) / 2
        val node = nodes[mid]

        node.left = buildTreeUtil(nodes, start, mid - 1)
        node.right = buildTreeUtil(nodes, mid + 1, end)

        return node
    }

    fun convertToAvlTree() {
        val nodes = mutableListOf<BSTNode>()
        storeBstNodes(root, nodes)
        root = buildTreeUtil(nodes, 0, nodes.size - 1)
    }


}