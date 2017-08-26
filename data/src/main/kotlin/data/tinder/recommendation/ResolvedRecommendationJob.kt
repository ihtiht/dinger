package data.tinder.recommendation

internal data class ResolvedRecommendationJob(
        val id: String,
        val company: ResolvedRecommendationCompany,
        val title: ResolvedRecommendationTitle) {
    companion object {
        val NONE = ResolvedRecommendationJob(
                id = "",
                company = ResolvedRecommendationCompany.NONE,
                title = ResolvedRecommendationTitle.NONE)
    }
}
