package data.tinder.recommendation

import data.database.CollectibleDaoDelegate
import domain.recommendation.DomainRecommendationInterest

internal class RecommendationInterestDaoDelegate(
        private val interestDao: RecommendationInterestDao,
        private val userInterestDao: RecommendationUser_InterestDao)
    : CollectibleDaoDelegate<String, DomainRecommendationInterest>() {
    override fun selectByPrimaryKey(primaryKey: String) =
            interestDao.selectInterestById(primaryKey).firstOrNull()?.let {
                return@let DomainRecommendationInterest(
                        id = it.id,
                        name = it.name)
            } ?: DomainRecommendationInterest.NONE

    override fun insertDomain(source: DomainRecommendationInterest) =
            interestDao.insertInterest(RecommendationInterestEntity(
                    id = source.id,
                    name = source.name))

    fun insertDomainForUserId(
            userId: String, interests: Iterable<DomainRecommendationInterest>) {
        interests.forEach {
            insertDomain(it)
            userInterestDao.insertUser_Interest(RecommendationUserEntity_RecommendationInterestEntity(
                    recommendationUserEntityId = userId,
                    recommendationInterestEntityId = it.id))
        }
    }
}
