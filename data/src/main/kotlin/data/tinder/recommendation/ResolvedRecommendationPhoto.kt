package data.tinder.recommendation

internal class ResolvedRecommendationPhoto(
        val id: String,
        val url: String,
        val processedFiles: Iterable<ResolvedRecommendationProcessedFile>)
