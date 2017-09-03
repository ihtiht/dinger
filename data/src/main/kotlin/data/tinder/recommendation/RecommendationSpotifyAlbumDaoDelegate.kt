package data.tinder.recommendation

import data.database.DaoDelegate
import domain.recommendation.DomainRecommendationSpotifyAlbum

internal class RecommendationSpotifyAlbumDaoDelegate(
        private val spotifyAlbumDao: RecommendationUserSpotifyThemeTrackAlbumDao,
        private val processedFileDaoDelegate: RecommendationProcessedFileDaoDelegate)
    : DaoDelegate<String, DomainRecommendationSpotifyAlbum>() {
    override fun selectByPrimaryKey(primaryKey: String) =
            spotifyAlbumDao.selectAlbumById(primaryKey).firstOrNull()?.let {
                val images = processedFileDaoDelegate.collectByPrimaryKeys(it.images)
                return@let DomainRecommendationSpotifyAlbum(
                        id = it.recommendationUserSpotifyThemeTrackAlbum.id,
                        name = it.recommendationUserSpotifyThemeTrackAlbum.name,
                        images = images)
            } ?: DomainRecommendationSpotifyAlbum.NONE

    override fun insertDomain(source: DomainRecommendationSpotifyAlbum) {
        processedFileDaoDelegate.insertDomainForAlbumId(source.id, source.images)
        spotifyAlbumDao.insertAlbum(RecommendationUserSpotifyThemeTrackAlbumEntity(
                id = source.id,
                name = source.name))
    }
}
