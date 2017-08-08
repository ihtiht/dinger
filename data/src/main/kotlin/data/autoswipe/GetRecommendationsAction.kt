package data.autoswipe

import data.autoswipe.AutoSwipeJobIntentService.CommonResultDelegate
import domain.interactor.DisposableUseCase
import domain.recommendation.DomainRecommendationCollection
import domain.recommendation.GetRecommendationsUseCase
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

internal class GetRecommendationsAction
    : AutoSwipeJobIntentService.Action<GetRecommendationsAction.Callback>  {
    private var useCaseDelegate: DisposableUseCase? = null
    private val resultDelegate = CommonResultDelegate(this)

    override fun execute(owner: AutoSwipeJobIntentService, callback: Callback) =
            GetRecommendationsUseCase(Schedulers.trampoline()).let {
                useCaseDelegate = it
                it.execute(object : DisposableSingleObserver<DomainRecommendationCollection>() {
                    override fun onSuccess(payload: DomainRecommendationCollection) {
                        resultDelegate.onComplete(owner)
                        callback.onRecommendationsReceived(payload)
                    }

                    override fun onError(error: Throwable) {
                        resultDelegate.onError(owner, error)
                    }
                })
            }

    override fun dispose() {
        useCaseDelegate?.dispose()
    }

    interface Callback {
        fun onRecommendationsReceived(recommendationCollection: DomainRecommendationCollection)
    }
}
