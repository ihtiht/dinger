package data.tinder.recommendation

internal data class ResolvedRecommendationSpotifyAlbum(
        val id: String,
        val name: String,
        val images: Iterable<ResolvedRecommendationProcessedFile>) {
    companion object {
        val NONE = ResolvedRecommendationSpotifyAlbum(id = "", name = "", images = emptySet())
    }
}
