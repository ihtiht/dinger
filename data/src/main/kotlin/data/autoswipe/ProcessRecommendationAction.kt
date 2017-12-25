package data.autoswipe

import data.tinder.dislike.DislikeRecommendationActionFactoryWrapper
import data.tinder.like.LikeRecommendationAction
import data.tinder.like.LikeRecommendationActionFactoryWrapper
import domain.like.DomainLikedRecommendationAnswer
import domain.recommendation.DomainRecommendationUser

internal class ProcessRecommendationAction(
        private val user: DomainRecommendationUser,
        private val likeRecommendationActionFactory: dagger.Lazy<LikeRecommendationActionFactoryWrapper>,
        private val dislikeRecommendationActionFactory
        : dagger.Lazy<DislikeRecommendationActionFactoryWrapper>)
    : AutoSwipeJobIntentService.Action<ProcessRecommendationAction.Callback>() {
    private var runningAction: AutoSwipeJobIntentService.Action<*>? = null

    override fun execute(owner: AutoSwipeJobIntentService, callback: Callback) =
            likeRecommendation(owner, callback)

    override fun dispose() {
        runningAction?.dispose()
    }

    private fun likeRecommendation(owner: AutoSwipeJobIntentService, callback: Callback) =
            likeRecommendationActionFactory.get().delegate(user).let {
                runningAction = it
                it.execute(
                        owner, object : LikeRecommendationAction.Callback {
                    override fun onRecommendationLiked(answer: DomainLikedRecommendationAnswer) {
                        commonDelegate.onComplete(owner)
                        callback.onRecommendationProcessed(answer)
                    }

                    override fun onRecommendationLikeFailed(error: Throwable) {
                        commonDelegate.onError(error, owner)
                        callback.onRecommendationProcessingFailed()
                    }
                })
    }

    interface Callback {
        fun onRecommendationProcessed(answer: DomainLikedRecommendationAnswer)

        fun onRecommendationProcessingFailed()
    }
}
