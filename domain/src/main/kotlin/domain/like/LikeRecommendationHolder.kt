package domain.like

object LikeRecommendationHolder {
    internal lateinit var likeRecommendationProvider: LikeRecommendationProvider

    fun likeRecommendationProvider(it: LikeRecommendationProvider) {
        likeRecommendationProvider = it
    }
}
