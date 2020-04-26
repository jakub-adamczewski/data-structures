abstract class Structure {

    abstract fun insert(value: Int)
    abstract fun remove()
    abstract fun findByValue(value: Int): Node?
}