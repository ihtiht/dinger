package data.tinder.recommendation

internal class ResolvedRecommendationCompany(val name: String) {
    companion object {
        val NONE = ResolvedRecommendationCompany(name = "")
    }
}
