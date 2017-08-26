package data.tinder.recommendation

internal data class ResolvedRecommendationInstagramPhoto(
        val link: String,
        val imageUrl: String,
        val thumbnailUrl: String,
        val ts: String) {
    companion object {
        val NONE = ResolvedRecommendationInstagramPhoto(
                link = "",
                imageUrl = "",
                thumbnailUrl = "",
                ts = "")
    }
}
