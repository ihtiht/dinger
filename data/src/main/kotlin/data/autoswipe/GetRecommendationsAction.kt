package data.autoswipe

import data.autoswipe.AutoSwipeJobIntentService.CommonResultDelegate
import domain.interactor.DisposableUseCase
import domain.recommendation.DomainRecommendation
import domain.recommendation.GetRecommendationsUseCase
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

internal class GetRecommendationsAction
    : AutoSwipeJobIntentService.Action<GetRecommendationsAction.Callback>  {
    private var useCaseDelegate: DisposableUseCase? = null
    private val commonDelegate = CommonResultDelegate(this)

    override fun execute(owner: AutoSwipeJobIntentService, callback: Callback) =
            GetRecommendationsUseCase(Schedulers.trampoline()).let {
                useCaseDelegate = it
                it.execute(object : DisposableSingleObserver<Collection<DomainRecommendation>>() {
                    override fun onSuccess(payload: Collection<DomainRecommendation>) {
                        commonDelegate.onComplete(owner)
                        callback.onRecommendationsReceived(payload)
                    }

                    override fun onError(error: Throwable) {
                        commonDelegate.onError(owner, error)
                    }
                })
            }

    override fun dispose() {
        useCaseDelegate?.dispose()
    }

    interface Callback {
        fun onRecommendationsReceived(recommendations: Collection<DomainRecommendation>)
    }
}
