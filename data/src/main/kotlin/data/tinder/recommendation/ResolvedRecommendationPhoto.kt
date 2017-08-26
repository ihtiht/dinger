package data.tinder.recommendation

internal data class ResolvedRecommendationPhoto(
        val id: String,
        val url: String,
        val processedFiles: Iterable<ResolvedRecommendationProcessedFile>) {
    companion object {
        val NONE = ResolvedRecommendationPhoto(id = "", url = "", processedFiles = emptySet())
    }
}
