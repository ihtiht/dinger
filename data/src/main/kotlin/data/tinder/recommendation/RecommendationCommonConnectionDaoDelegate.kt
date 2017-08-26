package data.tinder.recommendation

import data.CollectibleDaoDelegate

internal class RecommendationCommonConnectionDaoDelegate(
        private val commonConnectionDao: RecommendationUserCommonConnectionDao,
        private val userCommonConnectionDelegate
        : RecommendationUser_RecommendationUserCommonConnectionDao,
        private val photoDaoDelegate: CommonConnectionPhotoDaoDelegate)
    : CollectibleDaoDelegate<ResolvedRecommendationCommonConnection>() {
    override fun selectByPrimaryKey(primaryKey: String) =
            commonConnectionDao.selectCommonConnectionById(primaryKey).firstOrNull()?.let {
                val photos = photoDaoDelegate.collectByPrimaryKeys(it.photos)
                it.recommendationUserCommonConnection.let {
                    return ResolvedRecommendationCommonConnection(
                            id = it.id,
                            name = it.name,
                            degree = it.degree,
                            photos = photos)
                }
            } ?: ResolvedRecommendationCommonConnection.NONE

    override fun insertResolved(source: ResolvedRecommendationCommonConnection) {
        photoDaoDelegate.insertResolvedForCommonConnectionId(source.id, source.photos)
        commonConnectionDao.insertCommonConnection(
                RecommendationUserCommonConnectionEntity(
                            id = source.id,
                            name = source.name,
                            degree = source.degree))
    }

    fun insertResolvedForUserId(
            userId: String, commonConnections: Iterable<ResolvedRecommendationCommonConnection>) {
        commonConnections.forEach {
            insertResolved(it)
            userCommonConnectionDelegate.insertUser_CommonConnection(
                    RecommendationUserEntity_RecommendationUserCommonConnectionEntity(
                            recommendationUserEntityId = userId,
                            recommendationUserCommonConnectionEntityId = it.id))
        }
    }
}
