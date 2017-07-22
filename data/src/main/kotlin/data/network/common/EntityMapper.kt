package data.network.common

internal interface EntityMapper<in From, out To> {
    fun transform(source: From): To
}
