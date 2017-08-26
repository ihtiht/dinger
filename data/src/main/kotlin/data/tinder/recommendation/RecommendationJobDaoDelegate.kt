package data.tinder.recommendation

import android.arch.persistence.room.RoomDatabase
import data.CollectibleDaoDelegate

internal class RecommendationJobDaoDelegate(appDatabase: RoomDatabase)
    : CollectibleDaoDelegate<ResolvedRecommendationJob>(),
        RecommendationUserJobDao by RecommendationUserJobDao_Impl(appDatabase),
        RecommendationUser_JobDao by RecommendationUser_JobDao_Impl(appDatabase) {
    override fun insertResolved(source: ResolvedRecommendationJob) = insertJob(
            RecommendationUserJobEntity(
                    id = source.id,
                    company = RecommendationUserJobCompany(source.company.name),
                    title = RecommendationUserJobTitle(source.title.name)))

    fun insertResolvedForUserId(userId: String, jobs: Iterable<ResolvedRecommendationJob>) {
        jobs.forEach {
            insertResolved(it)
            insertUser_Job(RecommendationUserEntity_RecommendationUserJobEntity(
                    recommendationUserEntityId = userId,
                    recommendationUserJobEntityId = it.id))
        }
    }
}
