package data.network

internal interface EntityMapper<From, To> {
    fun from(source: From): To

    fun to(source: To): From {
        throw NotImplementedError("to(To) not implemented")
    }
}
