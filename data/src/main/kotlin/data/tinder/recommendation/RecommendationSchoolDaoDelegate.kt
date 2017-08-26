package data.tinder.recommendation

import data.CollectibleDaoDelegate

internal class RecommendationSchoolDaoDelegate(
        private val schoolDao: RecommendationUserSchoolDao,
        private val userSchoolDao: RecommendationUser_SchoolDao)
    : CollectibleDaoDelegate<String, ResolvedRecommendationSchool>() {
    override fun selectByPrimaryKey(primaryKey: String) =
            schoolDao.selectSchoolById(primaryKey).firstOrNull()?.let {
                return ResolvedRecommendationSchool(
                        id = it.id,
                        name = it.name)
            } ?: ResolvedRecommendationSchool.NONE

    override fun insertResolved(source: ResolvedRecommendationSchool) = schoolDao.insertSchool(
            RecommendationUserSchoolEntity(id = source.id, name = source.name))

    fun insertResolvedForUserId(userId: String, schools: Iterable<ResolvedRecommendationSchool>) {
        schools.forEach {
            insertResolved(it)
            userSchoolDao.insertUser_School(RecommendationUserEntity_RecommendationUserSchoolEntity(
                    recommendationUserEntityId = userId,
                    recommendationUserSchoolEntityId = it.id))
        }
    }
}
