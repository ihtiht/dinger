package data.tinder.recommendation

internal data class ResolvedRecommendationInterest(val id: String, val name: String) {
    companion object {
        val NONE = ResolvedRecommendationInterest(id = "", name = "")
    }
}
