package data.tinder.recommendation

import android.arch.persistence.room.RoomDatabase
import data.DaoDelegate

internal class RecommendationSpotifyThemeTrackDaoDelegate(
        appDatabase: RoomDatabase,
        private val spotifyArtistDaoDelegate: RecommendationSpotifyArtistDaoDelegate,
        private val spotifyAlbumDaoDelegate: RecommendationSpotifyAlbumDaoDelegate)
    : DaoDelegate<ResolvedRecommendationSpotifyThemeTrack>,
        RecommendationUserSpotifyThemeTrackDao
        by RecommendationUserSpotifyThemeTrackDao_Impl(appDatabase) {
    override fun selectByPrimaryKey(primaryKey: String) =
            selectSpotifyThemeTrackById(primaryKey).firstOrNull()?.let {
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
            } ?: ResolvedRecommendationSpotifyThemeTrack(
                    artists = emptySet(),
                    album = ResolvedRecommendationSpotifyAlbum(
                            id = "",
                            name = "",
                            images = emptySet()),
                    previewUrl = "",
                    name = "",
                    id = "",
                    uri = "")

    override fun insertResolved(source: ResolvedRecommendationSpotifyThemeTrack) {
        spotifyArtistDaoDelegate.insertResolvedForTrackId(source.id, source.artists)
        spotifyAlbumDaoDelegate.insertResolved(source.album)
        insertSpotifyThemeTrack(RecommendationUserSpotifyThemeTrackEntity(
                album = source.album.id,
                previewUrl = source.previewUrl,
                name = source.name,
                id = source.id,
                uri = source.uri))
    }
}
