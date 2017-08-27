package data.autoswipe

import android.content.Context
import android.content.Intent
import android.support.annotation.CallSuper
import android.support.v4.app.JobIntentService
import data.ComponentHolder
import domain.recommendation.DomainRecommendation
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

    interface Action<in T> {
        fun execute(owner: AutoSwipeJobIntentService, callback: T)

        fun dispose()
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
                    recommendations: Collection<DomainRecommendation>) {
                recommendations.stream().parallel().map {
                    // TODO Try to like the recommendation and based on the response, save it as liked/not liked
                }
            }
        })
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
