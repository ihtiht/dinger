package domain.recommendation

import domain.DomainHolder
import domain.interactor.SingleDisposableUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

class GetRecommendationsUseCase(
        preExecutionScheduler: Scheduler,
        postExecutionScheduler: Scheduler)
    : SingleDisposableUseCase<DomainRecommendationCollection>(postExecutionScheduler) {
    override fun buildUseCase(): Single<DomainRecommendationCollection>
            = DomainHolder.facadeProvider.tinderApiRepository().getRecommendations()
}
