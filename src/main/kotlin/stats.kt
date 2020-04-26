import kotlin.system.measureNanoTime

fun printStats() {
    printListQuestion()
    val size = readLine()!!.toInt()

    printPrintQuestion()
    val printStructures = when (readLine()!!.toLowerCase()) {
        "y" -> true
        "n" -> false
        else -> false
    }

    val testList = generateRandomShuffledList(size)
    val myLinkedList = SortedInsertLinkedList()
    val myBst = BinarySearchTree()

    val structuresCreationTimes = creatingStructuresTest(myLinkedList, myBst, testList, printStructures)
    val valuesSearchingTimes = valuesSearchingTest(myLinkedList, myBst, testList)
    val structuresRemovingTimes = structureRemovingTest(myLinkedList, myBst, printStructures)

    printTable(structuresCreationTimes, valuesSearchingTimes, structuresRemovingTimes)
}

private fun creatingStructuresTest(
    myLinkedList: SortedInsertLinkedList,
    myBst: BinarySearchTree,
    testList: List<Int>,
    printStructures: Boolean
): Times {

    var listCreationTime = 0L
    var bstCreationTime = 0L

    for (elem in testList) {
        listCreationTime += measureNanoTime { myLinkedList.insert(elem) }
        bstCreationTime += measureNanoTime { myBst.insert(elem) }
    }

    if (printStructures) {
        myLinkedList.print()
        myBst.print()
    }

    return Times(listCreationTime, bstCreationTime, "Creation")
}

private fun valuesSearchingTest(
    myLinkedList: SortedInsertLinkedList,
    myBst: BinarySearchTree,
    testList: List<Int>
): Times {
    var listSearchingTime = 0L
    var bstSearchingTime = 0L

    for (elem in testList) {
        listSearchingTime += measureNanoTime { myLinkedList.findByValue(elem) }
        bstSearchingTime += measureNanoTime { myBst.findByValue(elem) }
    }

    return Times(listSearchingTime, bstSearchingTime, "Searching")
}

private fun structureRemovingTest(
    myLinkedList: SortedInsertLinkedList,
    myBst: BinarySearchTree,
    printStructures: Boolean
): Times {
    val listDeleteTime = measureNanoTime { myLinkedList.remove() }
    val bstDeleteTime = measureNanoTime { myBst.remove() }

    if (printStructures) {
        printEmptyList(); myLinkedList.print()
        printEmptyBst(); myBst.print()
    }

    return Times(listDeleteTime, bstDeleteTime, "Removing")
}

private fun searchingCorrectnessTest(
    myLinkedList: SortedInsertLinkedList,
    myBst: BinarySearchTree,
    testList: List<Int>
): Boolean {

    var listTest: SortedInsertLinkedList.ListNode?
    var bstTest: BinarySearchTree.BSTNode?

    for (elem in testList) {
        listTest = myLinkedList.findByValue(elem)
        bstTest = myBst.findByValue(elem)

        if (listTest == null || bstTest == null) return false
    }

    return true

}

private fun printTable(creationTimes: Times, searchingTimes: Times, removingTimes: Times) {

    val lineFormat = "| %6s | %10s | %8s | %14s |%n"
    val line = "+--------+------------+----------+----------------+"
    println(line)
    System.out.format(lineFormat, "NAME", "OPERATION", "MILLIS", "NANOS")
    println(line)

    System.out.format(
        lineFormat,
        listName(),
        creationTimes.operationType,
        String.format("%,d", creationTimes.listTime.nanosToMillis()),
        String.format("%,d", creationTimes.listTime)
    )
    println(line)
    System.out.format(
        lineFormat,
        bstName(),
        creationTimes.operationType,
        String.format("%,d", creationTimes.bstTime.nanosToMillis()),
        String.format("%,d", creationTimes.bstTime)
    )
    println(line)
    System.out.format(
        lineFormat,
        listName(),
        searchingTimes.operationType,
        String.format("%,d", searchingTimes.listTime.nanosToMillis()),
        String.format("%,d", searchingTimes.listTime)
    )
    println(line)
    System.out.format(
        lineFormat,
        bstName(),
        searchingTimes.operationType,
        String.format("%,d", searchingTimes.bstTime.nanosToMillis()),
        String.format("%,d", searchingTimes.bstTime)
    )
    println(line)
    System.out.format(
        lineFormat,
        listName(),
        removingTimes.operationType,
        String.format("%,d", removingTimes.listTime.nanosToMillis()),
        String.format("%,d", removingTimes.listTime)
    )
    println(line)
    System.out.format(
        lineFormat,
        bstName(),
        removingTimes.operationType,
        String.format("%,d", removingTimes.bstTime.nanosToMillis()),
        String.format("%,d", removingTimes.bstTime)
    )
    println(line)

}


