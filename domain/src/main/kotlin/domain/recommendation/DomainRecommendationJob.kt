package domain.recommendation

data class DomainRecommendationJob(
        val id: String,
        val company: DomainRecommendationCompany,
        val title: DomainRecommendationTitle?) {
    companion object {
        val NONE = DomainRecommendationJob(
                id = "",
                company = DomainRecommendationCompany.NONE,
                title = DomainRecommendationTitle.NONE)
    }
}
