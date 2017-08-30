package data.tinder.recommendation

import data.DaoDelegate
import domain.recommendation.DomainRecommendationSpotifyThemeTrack

internal class RecommendationSpotifyThemeTrackDaoDelegate(
        private val spotifyThemeTrackDelegate: RecommendationUserSpotifyThemeTrackDao,
        private val spotifyArtistDaoDelegate: RecommendationSpotifyArtistDaoDelegate,
        private val spotifyAlbumDaoDelegate: RecommendationSpotifyAlbumDaoDelegate)
    : DaoDelegate<String?, DomainRecommendationSpotifyThemeTrack?>() {
    override fun selectByPrimaryKey(primaryKey: String?): DomainRecommendationSpotifyThemeTrack? {
        if (primaryKey == null) {
            return null
        }
        return spotifyThemeTrackDelegate.selectSpotifyThemeTrackById(primaryKey).firstOrNull()
                ?.let {
            val artists = spotifyArtistDaoDelegate.collectByPrimaryKeys(it.artists)
            it.recommendationUserSpotifyThemeTrackEntity.let {
                DomainRecommendationSpotifyThemeTrack(
                        artists = artists,
                        album = spotifyAlbumDaoDelegate.selectByPrimaryKey(it.album),
                        previewUrl = it.previewUrl,
                        name = it.name,
                        id = it.id,
                        uri = it.uri)
            }
        }
    }

    override fun insertDomain(source: DomainRecommendationSpotifyThemeTrack?) {
        if (source == null) {
            return
        }
        spotifyArtistDaoDelegate.insertDomainForTrackId(source.id, source.artists)
        spotifyAlbumDaoDelegate.insertDomain(source.album)
        spotifyThemeTrackDelegate.insertSpotifyThemeTrack(RecommendationUserSpotifyThemeTrackEntity(
                album = source.album.id,
                previewUrl = source.previewUrl,
                name = source.name,
                id = source.id,
                uri = source.uri))
    }
}
