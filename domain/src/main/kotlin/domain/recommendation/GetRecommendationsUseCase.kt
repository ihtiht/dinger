package domain.recommendation

import domain.DomainHolder
import domain.exec.PostExecutionSchedulerProvider
import domain.interactor.SingleDisposableUseCase
import io.reactivex.Single

class GetRecommendationsUseCase(postExecutionSchedulerProvider: PostExecutionSchedulerProvider)
    : SingleDisposableUseCase<DomainRecommendationCollection>(postExecutionSchedulerProvider) {
    override fun buildUseCase(): Single<DomainRecommendationCollection>
            = DomainHolder.facadeProvider.tinderApiRepository()
            .getRecommendations(DomainRecommendationRequestParameters())
}
