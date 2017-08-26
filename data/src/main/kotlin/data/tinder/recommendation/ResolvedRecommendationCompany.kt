package data.tinder.recommendation

internal data class ResolvedRecommendationCompany(val name: String) {
    companion object {
        val NONE = ResolvedRecommendationCompany(name = "")
    }
}
