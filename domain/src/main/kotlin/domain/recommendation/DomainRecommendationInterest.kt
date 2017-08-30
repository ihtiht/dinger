package domain.recommendation

data class DomainRecommendationInterest(val id: String, val name: String) {
    companion object {
        val NONE = DomainRecommendationInterest(id = "", name = "")
    }
}
