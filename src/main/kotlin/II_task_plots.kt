import scientifik.plotly.Plotly
import scientifik.plotly.makeFile
import scientifik.plotly.trace

fun generateSecondTaskPlots() {
    println(secondTaskPlots())

    val testList = generateRandomShuffledList(structuresSizes.max()!!)
    val bstInstances: List<BinarySearchTree> = structuresSizes.map { BinarySearchTree() }
    generateBstAndAvlHeightDifferencePlot(structuresSizes, testList, bstInstances)
}

private fun generateBstAndAvlHeightDifferencePlot(
    x: List<Int>,
    testList: List<Int>,
    bstInstances: List<BinarySearchTree>
) {
    val bstHeightsY: MutableList<Int> = mutableListOf()
    val avlHeightsY: MutableList<Int> = mutableListOf()

    for(i in x.indices){
        val currentBst = bstInstances[i]

        insertTestListIntoBst(currentBst, testList.take(x[i]))
        bstHeightsY.add(currentBst.getHeight())

        currentBst.convertToAvlTree()
        avlHeightsY.add(currentBst.getHeight())
    }

    val plot = Plotly.plot2D {
        trace(x, bstHeightsY) {
            name = bstHeightsAxisName()
        }
        trace(x, avlHeightsY) {
            name = avlHeightsAxisName()
        }

        layout {
            title = heightsComparisonTitle()
            xaxis {
                title = xAxisTitle()
            }
            yaxis {
                title = yAxisHeightsComparisonTitle()
            }
        }
    }
    plot.makeFile()




}

private fun insertTestListIntoBst(bst: BinarySearchTree, testList: List<Int>){
    testList.forEach{bst.insert(it)}
}