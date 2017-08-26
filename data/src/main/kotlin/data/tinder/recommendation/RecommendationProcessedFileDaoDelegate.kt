package data.tinder.recommendation

import data.CollectibleDaoDelegate

internal class RecommendationProcessedFileDaoDelegate(
        private val processedFileDao: RecommendationProcessedFileDao,
        private val photoProcessedFileDao: RecommendationPhoto_ProcessedFileDao,
        private val albumProcessedFileDao: RecommendationSpotifyAlbum_ProcessedFileDao)
    : CollectibleDaoDelegate<String, ResolvedRecommendationProcessedFile>() {
    override fun selectByPrimaryKey(primaryKey: String) =
            processedFileDao.selectProcessedFileByUrl(primaryKey).firstOrNull()?.let {
                return ResolvedRecommendationProcessedFile(
                        widthPx = it.widthPx,
                        url = it.url,
                        heightPx = it.heightPx)
            } ?: ResolvedRecommendationProcessedFile.NONE

    override fun insertResolved(source: ResolvedRecommendationProcessedFile) =
            processedFileDao.insertProcessedFile(RecommendationUserPhotoProcessedFileEntity(
                    widthPx = source.widthPx,
                    url = source.url,
                    heightPx = source.heightPx))

    fun insertResolvedForPhotoId(
            photoId: String, processedFiles: Iterable<ResolvedRecommendationProcessedFile>) {
        processedFiles.forEach {
            insertResolved(it)
            photoProcessedFileDao.insertPhoto_ProcessedFile(
                    RecommendationUserPhotoEntity_RecommendationUserPhotoProcessedFileEntity(
                            recommendationUserPhotoEntityId = photoId,
                            recommendationUserPhotoProcessedFileEntityUrl = it.url))
        }
    }

    fun insertResolvedForAlbumId(
            albumId: String, processedFiles: Iterable<ResolvedRecommendationProcessedFile>) {
        processedFiles.forEach {
            insertResolved(it)
            albumProcessedFileDao.insertSpotifyAlbum_ProcessedFile(
                    RecommendationUserSpotifyThemeTrackAlbumEntity_RecommendationUserPhotoProcessedFileEntity(
                            recommendationUserSpotifyThemeTrackAlbumEntityId = albumId,
                            recommendationUserPhotoProcessedFileEntityUrl = it.url))
        }
    }
}
