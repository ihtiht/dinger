package data.network

internal interface EntityMapper<in From, out To> {
    fun transform(source: From): To
}
