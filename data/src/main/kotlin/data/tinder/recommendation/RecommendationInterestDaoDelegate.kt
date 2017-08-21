package data.tinder.recommendation

import android.arch.persistence.room.RoomDatabase
import data.CollectibleDaoDelegate

internal class RecommendationInterestDaoDelegate(appDatabase: RoomDatabase)
    : CollectibleDaoDelegate<ResolvedRecommendationInterest>(),
        RecommendationInterestDao by RecommendationInterestDao_Impl(appDatabase),
        RecommendationUser_InterestDao by RecommendationUser_InterestDao_Impl(appDatabase) {
    override fun selectByPrimaryKey(primaryKey: String) =
            selectInterestById(primaryKey).firstOrNull()?.let {
                return@let ResolvedRecommendationInterest(id = it.id, name = it.name)
            } ?: ResolvedRecommendationInterest(id = "", name = "")

    override fun insertResolved(source: ResolvedRecommendationInterest) = insertInterest(
            RecommendationInterestEntity(id = source.id, name = source.name))

    fun insertResolvedForUserId(
            userId: String, interests: Iterable<ResolvedRecommendationInterest>) {
        interests.forEach {
            insertResolved(it)
            insertUser_Interest(RecommendationUserEntity_RecommendationInterestEntity(
                    recommendationUserEntityId = userId,
                    recommendationInterestEntityId = it.id))
        }
    }
}
