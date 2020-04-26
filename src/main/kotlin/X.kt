class Times(val listTime: Long, val bstTime: Long, val operationType: String)

val structuresSizes = listOf(
    1,
    10,
    100,
    1_000,
    5_000
//    ,
//    10_000,
//    15_000,
//    20_000,
//    25_000,
//    30_000,
//    35_000,
//    40_000,
//    45_000,
//    50_000,
//    55_000,
//    60_000
)

fun printListTitle() = print("Sorted linked list: ")

fun printBstTitle() = print("Binary search tree: ")

fun printEmptyList() = print("Empty list: ")

fun printEmptyBst() = print(" Empty BST: ")

fun printStart() = print("[")

fun printEnd() = println("]")

fun printListQuestion() = println("How long list of random and not repeating numbers do You want to use for test?")

fun printPrintQuestion() = println("Should I print created data structures for You? (y/n)")

fun findListValueError() = println("Node finding in lists works incorrectly.")

fun findBstValueError() = println("Node finding in BST works incorrectly.")

fun generateRandomShuffledList(size: Int): List<Int> {
    val randomList = MutableList(size) { element -> element }
    return randomList.shuffled()
}

fun Long.nanosToMillis() = this / 1_000_000

fun Long.millisToSeconds() = this / 1_000
