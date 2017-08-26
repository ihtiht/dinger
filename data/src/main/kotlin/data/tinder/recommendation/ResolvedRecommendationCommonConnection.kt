package data.tinder.recommendation

internal data class ResolvedRecommendationCommonConnection(
        val id: String,
        val name: String,
        val degree: String,
        val photos: Iterable<ResolvedRecommendationCommonConnectionPhoto>) {
    companion object {
        val NONE = ResolvedRecommendationCommonConnection(
                id = "",
                name = "",
                degree = "",
                photos = emptySet())
    }
}
