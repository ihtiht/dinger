package data.tinder.recommendation

internal data class ResolvedRecommendationTitle(val name: String) {
    companion object {
        val NONE = ResolvedRecommendationTitle(name = "")
    }
}
