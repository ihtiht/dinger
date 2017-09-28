package domain.recommendation

object GetRecommendationHolder {
    internal lateinit var getRecommendationProvider: GetRecommendationProvider

    fun getRecommendationProvider(it: GetRecommendationProvider) {
        getRecommendationProvider = it
    }
}
