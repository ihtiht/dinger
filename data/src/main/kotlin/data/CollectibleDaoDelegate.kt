package data

internal abstract class CollectibleDaoDelegate<T> : DaoDelegate<T>() {
    fun collectByPrimaryKeys(primaryKeys: Iterable<String>): Iterable<T> =
            primaryKeys.fold(emptySet(), { acc, s -> acc.plus(selectByPrimaryKey(s)) })
}
