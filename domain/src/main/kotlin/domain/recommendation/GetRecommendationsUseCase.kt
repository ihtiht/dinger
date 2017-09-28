package domain.recommendation

import domain.interactor.SingleDisposableUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

class GetRecommendationsUseCase(
        postExecutionScheduler: Scheduler)
    : SingleDisposableUseCase<Collection<DomainRecommendationUser>>(
        postExecutionScheduler = postExecutionScheduler) {
    override fun buildUseCase(): Single<Collection<DomainRecommendationUser>> =
            GetRecommendationHolder.getRecommendationProvider.getRecommendations()
}
