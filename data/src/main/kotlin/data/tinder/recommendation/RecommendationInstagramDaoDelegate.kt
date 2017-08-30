package data.tinder.recommendation

import data.DaoDelegate
import domain.recommendation.DomainRecommendationInstagram

internal class RecommendationInstagramDaoDelegate(
        private val instagramDao: RecommendationUserInstagramDao,
        private val instagramPhotoDaoDelegate: RecommendationInstagramPhotoDaoDelegate)
    : DaoDelegate<String?, DomainRecommendationInstagram?>() {
    override fun selectByPrimaryKey(primaryKey: String?): DomainRecommendationInstagram? {
        if (primaryKey == null) {
            return null
        }
        return instagramDao.selectInstagramByUsername(primaryKey).firstOrNull()?.let {
            val photos = instagramPhotoDaoDelegate.collectByPrimaryKeys(it.photos)
            it.recommendationUserInstagram.let {
                DomainRecommendationInstagram(
                        profilePictureUrl = it.profilePictureUrl,
                        lastFetchTime = it.lastFetchTime,
                        mediaCount = it.mediaCount,
                        completedInitialFetch = it.completedInitialFetch,
                        username = it.username,
                        photos = photos)
            }
        }
    }

    override fun insertDomain(source: DomainRecommendationInstagram?) {
        if (source == null) {
            return
        }
        instagramPhotoDaoDelegate.insertDomainForInstagramUsername(source.username, source.photos)
        instagramDao.insertInstagram(
                RecommendationUserInstagramEntity(
                        profilePictureUrl = source.profilePictureUrl,
                        lastFetchTime = source.lastFetchTime,
                        mediaCount = source.mediaCount,
                        completedInitialFetch = source.completedInitialFetch,
                        username = source.username))
    }
}
