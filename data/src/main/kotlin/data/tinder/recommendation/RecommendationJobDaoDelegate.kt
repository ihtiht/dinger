package data.tinder.recommendation

import data.CollectibleDaoDelegate
import domain.recommendation.DomainRecommendationCompany
import domain.recommendation.DomainRecommendationJob
import domain.recommendation.DomainRecommendationTitle

internal class RecommendationJobDaoDelegate(
        private val jobDao: RecommendationUserJobDao,
        private val userJobDao: RecommendationUser_JobDao)
    : CollectibleDaoDelegate<String, DomainRecommendationJob>() {
    override fun selectByPrimaryKey(primaryKey: String) =
            jobDao.selectJobById(primaryKey).firstOrNull()?.let {
                return@let DomainRecommendationJob(
                        id = it.id,
                        company = DomainRecommendationCompany(it.company.name),
                        title = DomainRecommendationTitle(it.title.name))
            } ?: DomainRecommendationJob.NONE

    override fun insertDomain(source: DomainRecommendationJob) = jobDao.insertJob(
            RecommendationUserJobEntity(
                    id = source.id,
                    company = RecommendationUserJobCompany(source.company.name),
                    title = RecommendationUserJobTitle(source.title.name)))

    fun insertDomainForUserId(userId: String, jobs: Iterable<DomainRecommendationJob>) {
        jobs.forEach {
            insertDomain(it)
            userJobDao.insertUser_Job(RecommendationUserEntity_RecommendationUserJobEntity(
                    recommendationUserEntityId = userId,
                    recommendationUserJobEntityId = it.id))
        }
    }
}
