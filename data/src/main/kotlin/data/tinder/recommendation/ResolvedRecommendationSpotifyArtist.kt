package data.tinder.recommendation

internal data class ResolvedRecommendationSpotifyArtist(val id: String, val name: String) {
    companion object {
        val NONE = ResolvedRecommendationSpotifyArtist(id = "", name = "")
    }
}
