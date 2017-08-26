package data

internal abstract class DaoDelegate<T> {
    open fun selectByPrimaryKey(primaryKey: String): T = throw NotImplementedError("Not implemented.")

    abstract fun insertResolved(source: T)
}
