package data.tinder.like

internal data class DataMatch(val something: Int) {
    private constructor() : this(0)

    companion object {
        val NO_MATCH = DataMatch()
    }
}
