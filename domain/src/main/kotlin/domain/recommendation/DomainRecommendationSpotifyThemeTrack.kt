package domain.recommendation

data class DomainRecommendationSpotifyThemeTrack(
        var artists: Set<DomainRecommendationSpotifyArtist>,
        var album: DomainRecommendationSpotifyAlbum,
        var previewUrl: String,
        var name: String,
        var id: String,
        var uri: String)
