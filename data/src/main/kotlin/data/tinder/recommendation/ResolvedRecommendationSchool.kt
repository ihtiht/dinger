package data.tinder.recommendation

internal class ResolvedRecommendationSchool(val id: String, val name: String) {
    companion object {
        val NONE = ResolvedRecommendationSchool(id = "", name = "")
    }
}
