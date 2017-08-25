package data.tinder.recommendation

import android.arch.persistence.room.RoomDatabase
import data.CollectibleDaoDelegate

internal class RecommendationSchoolDaoDelegate(appDatabase: RoomDatabase)
    : CollectibleDaoDelegate<ResolvedRecommendationSchool>(),
        RecommendationUserSchoolDao by RecommendationUserSchoolDao_Impl(appDatabase),
        RecommendationUser_SchoolDao by RecommendationUser_SchoolDao_Impl(appDatabase) {
    override fun selectByPrimaryKey(primaryKey: String) =
            selectSchoolById(primaryKey).firstOrNull()?.let {
                return@let ResolvedRecommendationSchool(id = it.id, name = it.name)
            } ?: ResolvedRecommendationSchool.NONE

    override fun insertResolved(source: ResolvedRecommendationSchool) = insertSchool(
            RecommendationUserSchoolEntity(id = source.id, name = source.name))

    fun insertResolvedForUserId(userId: String, schools: Iterable<ResolvedRecommendationSchool>) {
        schools.forEach {
            insertResolved(it)
            insertUser_School(RecommendationUserEntity_RecommendationUserSchoolEntity(
                    recommendationUserEntityId = userId,
                    recommendationUserSchoolEntityId = it.id))
        }
    }
}
