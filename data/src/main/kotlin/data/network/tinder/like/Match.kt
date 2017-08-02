package data.network.tinder.like

internal data class Match(val something: Int) {
    private constructor() : this(0)

    companion object {
        val NO_MATCH = Match()
    }
}
