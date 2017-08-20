package data.tinder.like

// TODO Try this without it being a data class
internal data class Match(val something: Int) {
    private constructor() : this(0)

    companion object {
        val NO_MATCH = Match()
    }
}
