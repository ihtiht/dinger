package domain.dislike

object DislikeRecommendationHolder {
    internal lateinit var likeRecommendationProvider: DislikeRecommendationProvider

    fun dislikeRecommendationProvider(it: DislikeRecommendationProvider) {
        likeRecommendationProvider = it
    }
}
