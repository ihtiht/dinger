package data

internal interface EntityMapper<in From, out To> {
    fun from(source: From): To
}
