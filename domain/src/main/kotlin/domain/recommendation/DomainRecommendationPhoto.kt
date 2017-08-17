package domain.recommendation

data class DomainRecommendationPhoto(
        val id: String,
        val url: String,
        val processedFiles: Set<DomainRecommendationProcessedFile>)
