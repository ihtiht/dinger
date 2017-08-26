package data.tinder.recommendation

import data.CollectibleDaoDelegate

internal class RecommendationTeaserDaoDelegate(
        private val teaserDao: RecommendationUserTeaserDao,
        private val userTeaserDao: RecommendationUser_TeaserDao)
    : CollectibleDaoDelegate<String, ResolvedRecommendationTeaser>() {
    override fun selectByPrimaryKey(primaryKey: String) =
            teaserDao.selectTeaserById(primaryKey).firstOrNull()?.let {
                return@let ResolvedRecommendationTeaser(
                        id = it.id,
                        description = it.description,
                        type = it.type)
            } ?: ResolvedRecommendationTeaser.NONE

    override fun insertResolved(source: ResolvedRecommendationTeaser) = teaserDao
            .insertTeaser(RecommendationUserTeaserEntity(
                    id = source.id,
                    description = source.description,
                    type = source.type))

    fun insertResolvedForUserId(userId: String, teasers: Iterable<ResolvedRecommendationTeaser>) {
        teasers.forEach {
            insertResolved(it)
            userTeaserDao.insertUser_Teaser(RecommendationUserEntity_RecommendationUserTeaserEntity(
                    recommendationUserEntityId = userId,
                    recommendationUserTeaserEntityId = it.id))
        }
    }
}
