package data.tinder.recommendation

internal class ResolvedRecommendationTitle(val name: String) {
    companion object {
        val NONE = ResolvedRecommendationTitle(name = "")
    }
}
