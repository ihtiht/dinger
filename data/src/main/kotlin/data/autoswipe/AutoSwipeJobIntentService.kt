package data.autoswipe

import android.content.Context
import android.content.Intent
import android.support.annotation.CallSuper
import android.support.v4.app.JobIntentService
import data.tinder.like.LikeRecommendationAction
import data.tinder.recommendation.RecommendationUserResolver
import domain.like.DomainLikedRecommendationAnswer
import domain.recommendation.DomainRecommendationUser
import javax.inject.Inject

internal class AutoSwipeJobIntentService : JobIntentService() {
    private var ongoingActions = emptySet<Action<*>>()
    @Inject
    lateinit var recommendationResolver: RecommendationUserResolver

    init {
        AutoSwipeComponentHolder.autoSwipeComponent.inject(this)
    }

    override fun onHandleWork(intent: Intent) = Unit.also { likeRecommendations() }

    override fun onStopCurrentWork() = true.also { releaseResources() }

    override fun onDestroy() {
        super.onDestroy()
        releaseResources()
    }

    abstract class Action<in Callback> {
        protected val commonDelegate by lazy { CommonResultDelegate(this) }

        abstract fun execute(owner: AutoSwipeJobIntentService, callback: Callback)

        abstract fun dispose()
    }

    class CommonResultDelegate(private val action: Action<*>) {
        @CallSuper
        fun onComplete(autoSwipeJobIntentService: AutoSwipeJobIntentService) {
            autoSwipeJobIntentService.clearAction(action)
        }

        @CallSuper
        fun onError(autoSwipeJobIntentService: AutoSwipeJobIntentService) {
            autoSwipeJobIntentService.clearAction(action)
            autoSwipeJobIntentService.scheduleBecauseError()
        }
    }

    private fun likeRecommendations() = GetRecommendationsAction().apply {
        ongoingActions += (this)
        execute(this@AutoSwipeJobIntentService, object : GetRecommendationsAction.Callback {
            override fun onRecommendationsReceived(
                    recommendations: List<DomainRecommendationUser>) {
                if (recommendations.isEmpty()) {
                    scheduleBecauseError()
                } else {
                    likeRecommendation(
                            recommendations.first(),
                            recommendations.subList(fromIndex = 1, toIndex = recommendations.size))
                }
            }
        })
    }

    private fun likeRecommendation(
            recommendation: DomainRecommendationUser,
            remaining: List<DomainRecommendationUser>) {
        LikeRecommendationAction(recommendation).apply {
            ongoingActions += (this)
            execute(this@AutoSwipeJobIntentService, object : LikeRecommendationAction.Callback {
                override fun onRecommendationLiked(answer: DomainLikedRecommendationAnswer) =
                        saveRecommendationToDatabase(
                                recommendation, liked = true, matched = answer.matched).also {
                            if (remaining.isEmpty()) {
                                scheduleBecauseMoreAvailable()
                            } else {
                                likeRecommendation(
                                        remaining.first(),
                                        remaining.subList(fromIndex = 1, toIndex = remaining.size))
                            }
                        }

                override fun onRecommendationLikeFailed() {
                    saveRecommendationToDatabase(recommendation, liked = false, matched = false)
                    scheduleBecauseLimited()
                }
            })
        }
    }

    private fun saveRecommendationToDatabase(
            recommendation: DomainRecommendationUser, liked: Boolean, matched: Boolean) {
        recommendationResolver.insert(DomainRecommendationUser(
                distanceMiles = recommendation.distanceMiles,
                commonConnections = recommendation.commonConnections,
                connectionCount = recommendation.connectionCount,
                id = recommendation.id,
                birthDate = recommendation.birthDate,
                name = recommendation.name,
                instagram = recommendation.instagram,
                teaser = recommendation.teaser,
                spotifyThemeTrack = recommendation.spotifyThemeTrack,
                gender = recommendation.gender,
                birthDateInfo = recommendation.birthDateInfo,
                contentHash = recommendation.contentHash,
                groupMatched = recommendation.groupMatched,
                pingTime = recommendation.pingTime,
                sNumber = recommendation.sNumber,
                liked = liked,
                matched = matched,
                commonInterests = recommendation.commonInterests,
                photos = recommendation.photos,
                jobs = recommendation.jobs,
                schools = recommendation.schools,
                teasers = recommendation.teasers))
    }

    private fun scheduleBecauseMoreAvailable() = ImmediatePostAutoSwipeAction().apply {
        ongoingActions += this
        execute(this@AutoSwipeJobIntentService, Unit)
    }

    private fun scheduleBecauseLimited() = DelayedPostAutoSwipeAction().apply {
        ongoingActions += this
        execute(this@AutoSwipeJobIntentService, Unit)
    }

    private fun scheduleBecauseError() = FromErrorPostAutoSwipeAction().apply {
        ongoingActions += this
        execute(this@AutoSwipeJobIntentService, Unit)
    }

    private fun releaseResources() {
        ongoingActions.forEach { it.dispose() }
        ongoingActions = emptySet()
    }

    private fun clearAction(action: Action<*>) = action.apply {
        dispose()
        ongoingActions -= this
    }

    companion object {
        private const val JOB_ID = 1000
        fun trigger(context: Context) = enqueueWork(
                context, AutoSwipeJobIntentService::class.java, JOB_ID, Intent())
    }
}
