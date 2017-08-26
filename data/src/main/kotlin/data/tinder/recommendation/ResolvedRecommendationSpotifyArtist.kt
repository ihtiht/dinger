package data.tinder.recommendation

internal data class ResolvedRecommendationSpotifyArtist(
        val name: String,
        val id: String) {
    companion object {
        val NONE = ResolvedRecommendationSpotifyArtist(name = "", id = "")
    }
}
