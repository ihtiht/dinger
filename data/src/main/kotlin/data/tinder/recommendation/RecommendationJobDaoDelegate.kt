package data.tinder.recommendation

import data.CollectibleDaoDelegate

internal class RecommendationJobDaoDelegate(
        private val jobDao: RecommendationUserJobDao,
        private val userJobDao: RecommendationUser_JobDao)
    : CollectibleDaoDelegate<String, ResolvedRecommendationJob>() {
    override fun insertResolved(source: ResolvedRecommendationJob) = jobDao.insertJob(
            RecommendationUserJobEntity(
                    id = source.id,
                    company = RecommendationUserJobCompany(source.company.name),
                    title = RecommendationUserJobTitle(source.title.name)))

    fun insertResolvedForUserId(userId: String, jobs: Iterable<ResolvedRecommendationJob>) {
        jobs.forEach {
            insertResolved(it)
            userJobDao.insertUser_Job(RecommendationUserEntity_RecommendationUserJobEntity(
                    recommendationUserEntityId = userId,
                    recommendationUserJobEntityId = it.id))
        }
    }
}
