class SortedInsertLinkedList : Structure() {

    class ListNode(val data: Int, var next: ListNode? = null) : Node()

    private var head: ListNode? = null

    override fun insert(value: Int) {
        val newNode = ListNode(value)

        /* Insertion at the beginning */
        if (head == null || head!!.data > value) {
            newNode.next = head
            head = newNode
            return
        }

        /* middle */
        var currentNode: ListNode? = head
        var previousNode: ListNode? = null
        while (currentNode != null) {
            if (currentNode.data > value) {
                previousNode!!.next = newNode
                newNode.next = currentNode
                return
            }
            previousNode = currentNode
            currentNode = currentNode.next
        }

        /* end */
        previousNode!!.next = newNode
        return
    }

    override fun remove() {
        while (head != null) removeAtZero()
    }

    private fun removeAtZero() {
        head = head!!.next
    }

    fun removeAt(index: Int) {
        var current: ListNode? = head
        var previous: ListNode? = null

        /* index == 0 */
        if (index == 0 && current != null) {
            head = current.next
            return
        }

        /* index > 0 && index < size */
        var counter = 0
        while (current != null) {

            if (counter == index) {
                previous!!.next = current.next
                return
            } else {
                previous = current
                current = current.next
                counter++
            }
        }

        /* index >= size  */
        if (current == null) return

    }

    override fun findByValue(value: Int): ListNode? {
        // Store head node
        var current: ListNode? = head
        var previous: ListNode? = null;

        /* If value is head data */
        if (current != null && current.data == value) {
            return current
        }

        /* If value is after head */
        while (current != null && current.data != value) {
            previous = current
            current = current.next
        }

        if (current != null) {
            return current
        }
        return null
    }

    fun print() {
        var currentNode = head
        printListTitle()
        printStart()
        while (currentNode != null) {
            print(" ${currentNode.data},")
            currentNode = currentNode.next
        }
        printEnd()
    }

}