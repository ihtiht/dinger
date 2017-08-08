package data.autoswipe

import data.autoswipe.AutoSwipeJobIntentService.CommonResultDelegate
import domain.interactor.DisposableUseCase
import domain.recommendation.DomainRecommendationCollection
import domain.recommendation.GetRecommendationsUseCase
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

internal class GetRecommendationsAction(private val owner: AutoSwipeJobIntentService)
    : AutoSwipeJobIntentService.Action  {
    private var useCaseDelegate: DisposableUseCase? = null
    private val resultDelegate = CommonResultDelegate(this)

    override fun execute() {
        GetRecommendationsUseCase(Schedulers.trampoline()).apply {
            useCaseDelegate = this
            execute(object : DisposableSingleObserver<DomainRecommendationCollection>() {
                override fun onSuccess(payload: DomainRecommendationCollection) {
                    // TODO Have a callback to tell the service to move on (it will start liking)
                    resultDelegate.onComplete(owner)
                }

                override fun onError(error: Throwable) {
                    resultDelegate.onError(owner, error)
                }
            })
        }
    }

    override fun dispose() {
        useCaseDelegate?.dispose()
    }
}
