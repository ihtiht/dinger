package data.tinder.recommendation

import android.arch.persistence.room.RoomDatabase
import data.DaoDelegate

internal class RecommendationSpotifyAlbumDaoDelegate(
        appDatabase: RoomDatabase,
        private val processedFileDaoDelegate: RecommendationProcessedFileDaoDelegate)
    : DaoDelegate<ResolvedRecommendationSpotifyAlbum>,
        RecommendationUserSpotifyThemeTrackAlbumDao
        by RecommendationUserSpotifyThemeTrackAlbumDao_Impl(appDatabase),
        RecommendationSpotifyAlbum_ProcessedFileDao
        by RecommendationSpotifyAlbum_ProcessedFileDao_Impl(appDatabase) {
    override fun selectByPrimaryKey(primaryKey: String) =
            selectAlbumById(primaryKey).firstOrNull()?.let {
                val images = processedFileDaoDelegate.collectByPrimaryKeys(it.images)
                return@let ResolvedRecommendationSpotifyAlbum(
                        id = it.recommendationUserSpotifyThemeTrackAlbum.id,
                        name = it.recommendationUserSpotifyThemeTrackAlbum.name,
                        images = images)
            } ?: ResolvedRecommendationSpotifyAlbum(id = "", name = "", images = emptySet())

    override fun insertResolved(source: ResolvedRecommendationSpotifyAlbum) {
        processedFileDaoDelegate.insertResolvedForAlbumId(source.id, source.images)
        insertAlbum(RecommendationUserSpotifyThemeTrackAlbumEntity(
                id = source.id,
                name = source.name))
    }
}
