package data

internal abstract class DaoDelegate<in PrimaryKey, Resolved> {
    open fun selectByPrimaryKey(primaryKey: PrimaryKey): Resolved =
            throw NotImplementedError("Not implemented.")

    abstract fun insertResolved(source: Resolved)
}
