package data.tinder.recommendation

import data.database.CollectibleDaoDelegate
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
                        company = it.company?.let { DomainRecommendationCompany(it.name) },
                        title = it.title?.let { DomainRecommendationTitle(it.name) })
            } ?: DomainRecommendationJob.NONE

    override fun insertDomain(source: DomainRecommendationJob) = jobDao.insertJob(
            RecommendationUserJobEntity(
                    id = source.id,
                    company = source.company?.let { RecommendationUserJobCompany(it.name) },
                    title = source.title?.let { RecommendationUserJobTitle(it.name) }))

    fun insertDomainForUserId(userId: String, jobs: Iterable<DomainRecommendationJob>) =
            jobs.forEach {
                insertDomain(it)
                userJobDao.insertUser_Job(RecommendationUserEntity_RecommendationUserJobEntity(
                        recommendationUserEntityId = userId,
                        recommendationUserJobEntityId = it.id))
            }
}
