package data.tinder.recommendation

internal class ResolvedRecommendationSpotifyThemeTrack(
        val artists: Iterable<ResolvedRecommendationSpotifyArtist>,
        val album: ResolvedRecommendationSpotifyAlbum,
        val previewUrl: String,
        val name: String,
        val id: String,
        val uri: String)
