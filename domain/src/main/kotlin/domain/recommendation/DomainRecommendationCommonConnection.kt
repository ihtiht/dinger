package domain.recommendation

data class DomainRecommendationCommonConnection(
        val id: String,
        val name: String,
        val degree: String,
        val photos: Iterable<DomainRecommendationCommonConnectionPhoto>) {
    companion object {
        val NONE = DomainRecommendationCommonConnection(
                id = "",
                name = "",
                degree = "",
                photos = emptySet())
    }
}
