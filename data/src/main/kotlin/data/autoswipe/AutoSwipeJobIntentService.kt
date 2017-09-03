package data.autoswipe

import android.content.Context
import android.content.Intent
import android.support.annotation.CallSuper
import android.support.v4.app.JobIntentService
import data.ComponentHolder
import data.tinder.like.LikeRecommendationAction
import domain.like.DomainLikedRecommendationAnswer
import domain.recommendation.DomainRecommendationUser
import reporter.CrashReporter
import javax.inject.Inject

internal class AutoSwipeJobIntentService : JobIntentService() {
    private val ongoingActions = mutableSetOf<Action<*>>()
    @Inject
    lateinit var crashReporter: CrashReporter

    init {
        ComponentHolder.autoSwipeComponent.inject(this)
    }

    override fun onHandleWork(intent: Intent) {
        requestRecommendations()
    }

    override fun onDestroy() {
        super.onDestroy()
        ongoingActions.apply {
            map { it.dispose() }
            clear()
        }
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
        fun onError(autoSwipeJobIntentService: AutoSwipeJobIntentService, error: Throwable) {
            autoSwipeJobIntentService.crashReporter.report(error)
            autoSwipeJobIntentService.clearAction(action)
            autoSwipeJobIntentService.scheduleFromError()
        }
    }

    private fun requestRecommendations() = GetRecommendationsAction().apply {
        ongoingActions.add(this)
        execute(this@AutoSwipeJobIntentService, object : GetRecommendationsAction.Callback {
            override fun onRecommendationsReceived(
                    recommendations: Collection<DomainRecommendationUser>) {
                recommendations.forEach { likeRecommendation(it) }
            }
        })
    }

    private fun likeRecommendation(recommendation: DomainRecommendationUser) =
            LikeRecommendationAction(recommendation).apply {
                ongoingActions.add(this)
                execute(this@AutoSwipeJobIntentService, object : LikeRecommendationAction.Callback {
                    override fun onRecommendationLiked(answer: DomainLikedRecommendationAnswer) {
                        saveRecommendationToDatabase(
                                recommendation, liked = true, matched = answer.matched)
                    }

                    override fun onRecommendationLikeFailed() {
                        saveRecommendationToDatabase(recommendation, liked = false, matched = false)
                    }
                })
            }

    private fun saveRecommendationToDatabase(
            recommendation: DomainRecommendationUser, liked: Boolean, matched: Boolean) {
        val recommendationToSave = DomainRecommendationUser(
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
                teasers = recommendation.teasers)
        // TODO saveRecommendationToDatabase
    }

    // TODO This needs to be called after getting rate-limit on swiping, not on recommend
    private fun scheduleHappySuccess() = DelayedPostAutoSwipeAction().apply {
        ongoingActions.add(this)
        execute(this@AutoSwipeJobIntentService, Unit)
    }

    private fun scheduleFromError() = FromErrorPostAutoSwipeAction().apply {
        ongoingActions.add(this)
        execute(this@AutoSwipeJobIntentService, Unit)
    }

    private fun clearAction(action: Action<*>) = action.apply {
        dispose()
        ongoingActions.remove(this)
    }

    companion object {
        private const val JOB_ID = 1000
        fun trigger(context: Context) = enqueueWork(
                context, AutoSwipeJobIntentService::class.java, JOB_ID, Intent())
        }
}
