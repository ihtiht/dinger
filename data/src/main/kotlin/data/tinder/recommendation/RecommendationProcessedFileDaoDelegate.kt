package data.tinder.recommendation

import android.arch.persistence.room.RoomDatabase
import data.CollectibleDaoDelegate

internal class RecommendationProcessedFileDaoDelegate(appDatabase: RoomDatabase)
    : CollectibleDaoDelegate<ResolvedRecommendationProcessedFile>(),
        RecommendationProcessedFileDao by RecommendationProcessedFileDao_Impl(appDatabase),
        RecommendationPhoto_ProcessedFileDao
        by RecommendationPhoto_ProcessedFileDao_Impl(appDatabase),
        RecommendationSpotifyAlbum_ProcessedFileDao
        by RecommendationSpotifyAlbum_ProcessedFileDao_Impl(appDatabase) {
    override fun selectByPrimaryKey(primaryKey: String) =
            selectProcessedFileByUrl(primaryKey).firstOrNull()?.let {
                return@let ResolvedRecommendationProcessedFile(
                        widthPx = it.widthPx,
                        url = it.url,
                        heightPx = it.heightPx)
            } ?: ResolvedRecommendationProcessedFile(widthPx = 0, url = "", heightPx = 0)

    override fun insertResolved(source: ResolvedRecommendationProcessedFile) = insertProcessedFile(
            RecommendationUserPhotoProcessedFileEntity(
                    widthPx = source.widthPx,
                    url = source.url,
                    heightPx = source.heightPx))

    fun insertResolvedForPhotoId(
            photoId: String, processedFiles: Iterable<ResolvedRecommendationProcessedFile>) {
        processedFiles.forEach {
            insertResolved(it)
            insertPhoto_ProcessedFile(
                    RecommendationUserPhotoEntity_RecommendationUserPhotoProcessedFileEntity(
                            recommendationUserPhotoEntityId = photoId,
                            recommendationUserPhotoProcessedFileEntityUrl = it.url))
        }
    }

    fun insertResolvedForAlbumId(
            albumId: String, processedFiles: Iterable<ResolvedRecommendationProcessedFile>) {
        processedFiles.forEach {
            insertResolved(it)
            insertSpotifyAlbum_ProcessedFile(
                    RecommendationUserSpotifyThemeTrackAlbumEntity_RecommendationUserPhotoProcessedFileEntity(
                            recommendationUserSpotifyThemeTrackAlbumEntityId = albumId,
                            recommendationUserPhotoProcessedFileEntityUrl = it.url))
        }
    }
}
