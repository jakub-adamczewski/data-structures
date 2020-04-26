import scientifik.plotly.Plotly
import scientifik.plotly.makeFile
import scientifik.plotly.trace
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

fun generateFirstTaskPlots() {
    println(firstTaskPlots())

    val testList = generateRandomShuffledList(structuresSizes.max()!!)

    val listInstances: List<SortedInsertLinkedList> = structuresSizes.map { SortedInsertLinkedList() }
    val bstInstances: List<BinarySearchTree> = structuresSizes.map { BinarySearchTree() }

    generateCreationTestPlot(structuresSizes, testList, listInstances, bstInstances)
    generateSearchingTestPlot(structuresSizes, testList, listInstances, bstInstances)
    generateRemovingTestPlot(structuresSizes, listInstances, bstInstances)


}

private fun generateCreationTestPlot(
    x: List<Int>,
    testList: List<Int>,
    listInstances: List<SortedInsertLinkedList>,
    bstInstances: List<BinarySearchTree>
) {
    val listY: MutableList<Long> = mutableListOf()
    val bstY: MutableList<Long> = mutableListOf()

    for (i in x.indices) {
        listY.add(creationTimeForTestList(listInstances[i], testList.take(x[i])))
        bstY.add(creationTimeForTestList(bstInstances[i], testList.take(x[i])))
    }

    val plot = Plotly.plot2D {
        trace(x, listY) {
            name = listName()
        }
        trace(x, bstY) {
            name = bstName()
        }

        layout {
            title = creationTitle()
            xaxis {
                title = xAxisTitle()
            }
            yaxis {
                title = yAxisCreationTitle()
            }
        }
    }
    plot.makeFile()
}

private fun creationTimeForTestList(structure: Structure, testList: List<Int>): Long {
    var time = 0L
    for(value in testList){
        time += measureTimeMillis { structure.insert(value) }
    }

    return time
}

private fun generateSearchingTestPlot(
    x: List<Int>,
    testList: List<Int>,
    listInstances: List<SortedInsertLinkedList>,
    bstInstances: List<BinarySearchTree>
) {

    val listY: MutableList<Long> = mutableListOf()
    val bstY: MutableList<Long> = mutableListOf()

    for (i in x.indices) {
        listY.add(searchingTimeForTestList(listInstances[i], testList.take(x[i])))
        bstY.add(searchingTimeForTestList(bstInstances[i], testList.take(x[i])))
    }

    val plot = Plotly.plot2D {
        trace(x, listY) {
            name = listName()
        }
        trace(x, bstY) {
            name = bstName()
        }

        layout {
            title = searchingTitle()
            xaxis {
                title = xAxisTitle()
            }
            yaxis {
                title = yAxisSearchingTitle()
            }
        }
    }
    plot.makeFile()
}

private fun searchingTimeForTestList(structure: Structure, testList: List<Int>): Long {
    var time = 0L
    for(value in testList){
        time += measureTimeMillis { structure.findByValue(value) }
    }

    return time
}

private fun generateRemovingTestPlot(
    x: List<Int>,
    listInstances: List<SortedInsertLinkedList>,
    bstInstances: List<BinarySearchTree>
) {

    val listY: MutableList<Long> = mutableListOf()
    val bstY: MutableList<Long> = mutableListOf()

    for (i in x.indices) {
        listY.add(removingTimeForStructureSize(listInstances[i]))
        bstY.add(removingTimeForStructureSize(bstInstances[i]))
    }

    val plot = Plotly.plot2D {
        trace(x, listY) {
            name = listName()
        }
        trace(x, bstY) {
            name = bstName()
        }

        layout {
            title = removalTitle()
            xaxis {
                title = xAxisTitle()
            }
            yaxis {
                title = yAxisRemovalTitle()
            }
        }
    }
    plot.makeFile()
}

private fun removingTimeForStructureSize(structure: Structure): Long {
    return measureNanoTime { structure.remove() }
}



