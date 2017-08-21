package data

internal interface DaoDelegate<T> {
    fun selectByPrimaryKey(primaryKey: String): T

    fun insertResolved(source: T)
}
