package domain.recommendation

data class DomainRecommendationTitle(val name: String) {
    companion object {
        val NONE = DomainRecommendationTitle("")
    }
}
