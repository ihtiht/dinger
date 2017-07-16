package data.network.common

internal interface EntityMapper<in From, out To> {
    fun map(source: From): To
}
