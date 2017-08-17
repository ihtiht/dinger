package domain.recommendation

data class DomainRecommendationSpotifyAlbum(
        val id: String,
        val name: String,
        val images: Set<DomainRecommendationProcessedFile>)
