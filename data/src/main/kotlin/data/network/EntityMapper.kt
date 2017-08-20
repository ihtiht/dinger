package data.network

internal interface EntityMapper<From, To> {
    fun from(source: From): To
}
