package data.tinder.like

import data.autoswipe.AutoSwipeJobIntentService
import domain.interactor.DisposableUseCase
import domain.like.DomainLikedRecommendationAnswer
import domain.like.LikeRecommendationUseCase
import domain.recommendation.DomainRecommendationUser
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import reporter.CrashReporter

internal class LikeRecommendationAction(
        private val user: DomainRecommendationUser,
        crashReporter: CrashReporter)
    : AutoSwipeJobIntentService.Action<LikeRecommendationAction.Callback>(crashReporter)  {
    private var useCaseDelegate: DisposableUseCase? = null

    override fun execute(owner: AutoSwipeJobIntentService, callback: Callback) =
            LikeRecommendationUseCase(user, Schedulers.trampoline()).let {
                useCaseDelegate = it
                it.execute(object : DisposableSingleObserver<DomainLikedRecommendationAnswer>() {
                    override fun onSuccess(payload: DomainLikedRecommendationAnswer) {
                        commonDelegate.onComplete(owner)
                        callback.onRecommendationLiked(payload)
                    }

                    override fun onError(error: Throwable) {
                        commonDelegate.onError(error, owner)
                        callback.onRecommendationLikeFailed()
                    }
                })
            }

    override fun dispose() {
        useCaseDelegate?.dispose()
    }

    interface Callback {
        fun onRecommendationLiked(answer: DomainLikedRecommendationAnswer)

        fun onRecommendationLikeFailed()
    }
}
