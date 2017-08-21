package data.tinder.recommendation

import android.arch.persistence.room.RoomDatabase
import data.CollectibleDaoDelegate

internal class RecommendationTeaserDaoDelegate(appDatabase: RoomDatabase)
    : CollectibleDaoDelegate<ResolvedRecommendationTeaser>(),
        RecommendationUserTeaserDao by RecommendationUserTeaserDao_Impl(appDatabase),
        RecommendationUser_TeaserDao by RecommendationUser_TeaserDao_Impl(appDatabase) {
    override fun selectByPrimaryKey(primaryKey: String) =
            selectTeaserById(primaryKey).firstOrNull()?.let {
                return@let ResolvedRecommendationTeaser(
                        id = it.id,
                        description = it.description,
                        type = it.type)
            } ?: ResolvedRecommendationTeaser(id = "", description = "", type = "")

    override fun insertResolved(source: ResolvedRecommendationTeaser) = insertTeaser(
            RecommendationUserTeaserEntity(
                    id = source.id,
                    description = source.description,
                    type = source.type))

    fun insertResolvedForUserId(userId: String, teasers: Iterable<ResolvedRecommendationTeaser>) {
        teasers.forEach {
            insertResolved(it)
            insertUser_Teaser(RecommendationUserEntity_RecommendationUserTeaserEntity(
                    recommendationUserEntityId = userId,
                    recommendationUserTeaserEntityId = it.id))
        }
    }
}
