package data.tinder.recommendations

internal data class DataRecommendation(val something: Int) {
    private constructor() : this(0)

    companion object {
        val EMPTY = DataRecommendation()
    }
}
