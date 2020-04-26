fun main() {
    println(firstMenuQuestion())
    when (readLine()!!.toInt()) {
        1 -> printStats()
        2 -> {
            generateFirstTaskPlots()
            generateSecondTaskPlots()
        }
        else -> printStats()
    }
}






