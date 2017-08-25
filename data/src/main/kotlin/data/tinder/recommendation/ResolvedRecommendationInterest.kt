package data.tinder.recommendation

internal class ResolvedRecommendationInterest(val id: String, val name: String) {
    companion object {
        val NONE = ResolvedRecommendationInterest(id = "", name = "")
    }
}
