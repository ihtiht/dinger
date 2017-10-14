package data.autoswipe

import android.content.Context
import android.content.Intent
import android.support.annotation.CallSuper
import android.support.v4.app.JobIntentService
import data.tinder.like.LikeRecommendationAction
import data.tinder.recommendation.RecommendationUserResolver
import domain.like.DomainLikedRecommendationAnswer
import domain.recommendation.DomainRecommendationUser
import reporter.CrashReporter
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

internal class AutoSwipeJobIntentService : JobIntentService() {
    private var ongoingActions = emptySet<Action<*>>()
    @Inject
    lateinit var crashReporter: CrashReporter
    @Inject
    lateinit var recommendationResolver: RecommendationUserResolver

    init {
        AutoSwipeComponentHolder.autoSwipeComponent.inject(this)
    }

    override fun onHandleWork(intent: Intent) {
        crashReporter.report(AutoSwipeStartedTrackedException(
                "onHandleWork starting at " +
                        SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH)
                                .format(System.currentTimeMillis())))
        likeRecommendations()
    }

    override fun onStopCurrentWork() = true.also { releaseResources() }

    override fun onDestroy() {
        super.onDestroy()
        releaseResources()
        crashReporter.report(AutoSwipeDestroyedTrackedException(
                "onDestroy finishing at " +
                        SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH)
                                .format(System.currentTimeMillis())))
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
            autoSwipeJobIntentService.crashReporter.report(Throwable(
                "From ${autoSwipeJobIntentService::class.java.name}", error))
            autoSwipeJobIntentService.clearAction(action)
            autoSwipeJobIntentService.scheduleBecauseError()
        }
    }

    private fun likeRecommendations() = GetRecommendationsAction().apply {
        ongoingActions += (this)
        execute(this@AutoSwipeJobIntentService, object : GetRecommendationsAction.Callback {
            override fun onRecommendationsReceived(
                    recommendations: Collection<DomainRecommendationUser>) {
                crashReporter.report(AutoSwipeGotRecommendationsTrackedException(
                        "Got recommendations with size ${recommendations.size}"))
                if (recommendations.isEmpty()) {
                    scheduleBecauseError()
                } else {
                    recommendations.forEach { likeRecommendation(it) }
                    scheduleBecauseMoreAvailable()
                }
            }
        })
    }

    private fun likeRecommendation(recommendation: DomainRecommendationUser) =
            LikeRecommendationAction(recommendation).apply {
                ongoingActions += (this)
                execute(this@AutoSwipeJobIntentService, object : LikeRecommendationAction.Callback {
                    override fun onRecommendationLiked(answer: DomainLikedRecommendationAnswer) =
                            saveRecommendationToDatabase(
                                    recommendation, liked = true, matched = answer.matched)

                    override fun onRecommendationLikeFailed() {
                        saveRecommendationToDatabase(recommendation, liked = false, matched = false)
                        scheduleBecauseLimited()
                    }
                })
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

    private fun scheduleBecauseLimited() = DelayedPostAutoSwipeAction(crashReporter).apply {
        ongoingActions += this
        execute(this@AutoSwipeJobIntentService, Unit)
    }

    private fun scheduleBecauseError() = FromErrorPostAutoSwipeAction(crashReporter).apply {
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

    private class AutoSwipeStartedTrackedException(message: String) : Throwable(message)

    private class AutoSwipeDestroyedTrackedException(message: String) : Throwable(message)

    private class AutoSwipeGotRecommendationsTrackedException(message: String) : Throwable(message)
}
