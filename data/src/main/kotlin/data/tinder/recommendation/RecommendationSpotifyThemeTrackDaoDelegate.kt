package data.tinder.recommendation

import data.DaoDelegate

internal class RecommendationSpotifyThemeTrackDaoDelegate(
        private val spotifyThemeTrackDelegate: RecommendationUserSpotifyThemeTrackDao,
        private val spotifyArtistDaoDelegate: RecommendationSpotifyArtistDaoDelegate,
        private val spotifyAlbumDaoDelegate: RecommendationSpotifyAlbumDaoDelegate)
    : DaoDelegate<ResolvedRecommendationSpotifyThemeTrack>() {
    override fun selectByPrimaryKey(primaryKey: String) =
            spotifyThemeTrackDelegate.selectSpotifyThemeTrackById(primaryKey).firstOrNull()?.let {
                val artists = spotifyArtistDaoDelegate.collectByPrimaryKeys(it.artists)
                it.recommendationUserSpotifyThemeTrackEntity.let {
                    return ResolvedRecommendationSpotifyThemeTrack(
                            artists = artists,
                            album = spotifyAlbumDaoDelegate.selectByPrimaryKey(it.album),
                            previewUrl = it.previewUrl,
                            name = it.name,
                            id = it.id,
                            uri = it.uri)
                }
            } ?: ResolvedRecommendationSpotifyThemeTrack.NONE

    override fun insertResolved(source: ResolvedRecommendationSpotifyThemeTrack) {
        spotifyArtistDaoDelegate.insertResolvedForTrackId(source.id, source.artists)
        spotifyAlbumDaoDelegate.insertResolved(source.album)
        spotifyThemeTrackDelegate.insertSpotifyThemeTrack(RecommendationUserSpotifyThemeTrackEntity(
                album = source.album.id,
                previewUrl = source.previewUrl,
                name = source.name,
                id = source.id,
                uri = source.uri))
    }
}
