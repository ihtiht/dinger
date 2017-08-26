package data.tinder.recommendation

import data.CollectibleDaoDelegate

internal class RecommendationCommonConnectionDaoDelegate(
        private val commonConnectionDelegate: RecommendationUserCommonConnectionDao,
        private val userCommonConnectionDelegate
        : RecommendationUser_RecommendationUserCommonConnectionDao,
        private val photoDaoDelegate: CommonConnectionPhotoDaoDelegate)
    : CollectibleDaoDelegate<ResolvedRecommendationCommonConnection>() {
    override fun selectByPrimaryKey(primaryKey: String) =
            commonConnectionDelegate.selectCommonConnectionById(primaryKey).firstOrNull()?.let {
                val photos = it.photos.map { ResolvedRecommendationCommonConnectionPhoto(
                        small = it.small,
                        medium = it.medium,
                        large = it.large)
                }
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
        commonConnectionDelegate.insertCommonConnection(
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
