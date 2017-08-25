package data.tinder.recommendation

internal class ResolvedRecommendationTeaser(
        val id: String,
        val description: String,
        val type: String) {
    companion object {
        val NONE = ResolvedRecommendationTeaser(id = "", description = "", type = "")
    }
}
