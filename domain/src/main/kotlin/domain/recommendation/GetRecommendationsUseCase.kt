package domain.recommendation

import domain.DomainHolder
import domain.interactor.SingleDisposableUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

class GetRecommendationsUseCase(
        postExecutionScheduler: Scheduler)
    : SingleDisposableUseCase<Collection<DomainRecommendation>>(
        postExecutionScheduler = postExecutionScheduler) {
    override fun buildUseCase(): Single<Collection<DomainRecommendation>> =
            DomainHolder.facadeProvider.tinderApiRepository().getRecommendations()
}
