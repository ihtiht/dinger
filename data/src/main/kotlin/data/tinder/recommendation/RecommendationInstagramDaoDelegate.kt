package data.tinder.recommendation

import data.DaoDelegate

internal class RecommendationInstagramDaoDelegate(
        private val instagramDao: RecommendationUserInstagramDao,
        private val instagramPhotoDaoDelegate: RecommendationInstagramPhotoDaoDelegate)
    : DaoDelegate<String?, ResolvedRecommendationInstagram?>() {
    override fun selectByPrimaryKey(primaryKey: String?): ResolvedRecommendationInstagram? {
        if (primaryKey == null) {
            return null
        }
        return instagramDao.selectInstagramByUsername(primaryKey).firstOrNull()?.let {
            val photos = instagramPhotoDaoDelegate.collectByPrimaryKeys(it.photos)
            it.recommendationUserInstagram.let {
                ResolvedRecommendationInstagram(
                        profilePictureUrl = it.profilePictureUrl,
                        lastFetchTime = it.lastFetchTime,
                        mediaCount = it.mediaCount,
                        completedInitialFetch = it.completedInitialFetch,
                        username = it.username,
                        photos = photos)
            }
        }
    }

    override fun insertResolved(source: ResolvedRecommendationInstagram?) {
        if (source == null) {
            return
        }
        instagramPhotoDaoDelegate.insertResolvedForInstagramUsername(source.username, source.photos)
        instagramDao.insertInstagram(
                RecommendationUserInstagramEntity(
                        profilePictureUrl = source.profilePictureUrl,
                        lastFetchTime = source.lastFetchTime,
                        mediaCount = source.mediaCount,
                        completedInitialFetch = source.completedInitialFetch,
                        username = source.username))
    }
}
