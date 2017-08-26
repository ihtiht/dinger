package data.tinder.recommendation

internal data class ResolvedRecommendationPhoto(
        val id: String,
        val url: String,
        val processedFiles: Iterable<ResolvedRecommendationProcessedFile>)
