package data.tinder.recommendation

import data.DaoDelegate

internal class RecommendationSpotifyAlbumDaoDelegate(
        private val spotifyAlbumDao: RecommendationUserSpotifyThemeTrackAlbumDao,
        private val processedFileDaoDelegate: RecommendationProcessedFileDaoDelegate)
    : DaoDelegate<ResolvedRecommendationSpotifyAlbum>() {
    override fun selectByPrimaryKey(primaryKey: String) =
            spotifyAlbumDao.selectAlbumById(primaryKey).firstOrNull()?.let {
                val images = processedFileDaoDelegate.collectByPrimaryKeys(it.images)
                return@let ResolvedRecommendationSpotifyAlbum(
                        id = it.recommendationUserSpotifyThemeTrackAlbum.id,
                        name = it.recommendationUserSpotifyThemeTrackAlbum.name,
                        images = images)
            } ?: ResolvedRecommendationSpotifyAlbum.NONE

    override fun insertResolved(source: ResolvedRecommendationSpotifyAlbum) {
        processedFileDaoDelegate.insertResolvedForAlbumId(source.id, source.images)
        spotifyAlbumDao.insertAlbum(RecommendationUserSpotifyThemeTrackAlbumEntity(
                id = source.id,
                name = source.name))
    }
}
