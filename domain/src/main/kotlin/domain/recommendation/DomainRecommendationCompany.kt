package domain.recommendation

data class DomainRecommendationCompany(val name: String) {
    companion object {
        val NONE = DomainRecommendationCompany(name = "")
    }
}
