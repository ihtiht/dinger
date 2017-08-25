package data.tinder.recommendation

internal class ResolvedRecommendationSpotifyArtist(
        val name: String,
        val id: String) {
    companion object {
        val NONE = ResolvedRecommendationSpotifyArtist(name = "", id = "")
    }
}
