package data.tinder.dislike

import data.autoswipe.AutoSwipeJobIntentService
import domain.dislike.DislikeRecommendationUseCase
import domain.dislike.DomainDislikedRecommendationAnswer
import domain.interactor.DisposableUseCase
import domain.recommendation.DomainRecommendationUser
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

internal class DislikeRecommendationAction(private val user: DomainRecommendationUser)
    : AutoSwipeJobIntentService.Action<DislikeRecommendationAction.Callback>() {
    private var useCaseDelegate: DisposableUseCase? = null

    override fun execute(owner: AutoSwipeJobIntentService, callback: Callback) =
            DislikeRecommendationUseCase(user, Schedulers.trampoline()).let {
                useCaseDelegate = it
                it.execute(object : DisposableSingleObserver<DomainDislikedRecommendationAnswer>() {
                    override fun onSuccess(payload: DomainDislikedRecommendationAnswer) {
                        callback.onRecommendationDisliked(payload)
                    }

                    override fun onError(error: Throwable) {
                        callback.onRecommendationLikeFailed()
                    }
                })
            }

    override fun dispose() {
        useCaseDelegate?.dispose()
    }

    interface Callback {
        fun onRecommendationDisliked(answer: DomainDislikedRecommendationAnswer)

        fun onRecommendationLikeFailed()
    }
}
