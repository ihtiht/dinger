package data.autoswipe

import android.content.Context
import android.content.Intent
import android.support.annotation.CallSuper
import android.support.v4.app.JobIntentService
import com.google.firebase.crash.FirebaseCrash

internal class AutoSwipeJobIntentService : JobIntentService() {
    private val actions = mutableSetOf<Action>()

    override fun onHandleWork(intent: Intent) {
        requestRecommendations()
    }

    override fun onDestroy() {
        super.onDestroy()
        actions.apply {
            map { it.dispose() }
            clear()
        }
    }

    private fun requestRecommendations() = GetRecommendationsAction(this).apply {
        actions.add(this)
        execute()
    }

    // TODO This needs to be called after getting rate-limit on swiping, not on recommend
    private fun scheduleHappySuccess() = DelayedPostAutoSwipeAction(this).apply {
        actions.add(this)
        execute()
    }

    private fun scheduleFromError() = FromErrorPostAutoSwipeAction(this).apply {
        actions.add(this)
        execute()
    }

    interface Action {
        fun execute()

        fun dispose()
    }

    class CommonResultDelegate(private val action: Action) {
        @CallSuper
        fun onComplete(autoSwipeJobIntentService: AutoSwipeJobIntentService) {
            autoSwipeJobIntentService.clearAction(action)
        }

        @CallSuper
        fun onError(autoSwipeJobIntentService: AutoSwipeJobIntentService, error: Throwable) {
            FirebaseCrash.report(error)
            autoSwipeJobIntentService.clearAction(action)
            autoSwipeJobIntentService.scheduleFromError()
        }
    }

    private fun clearAction(action: Action) = action.apply {
        dispose()
        actions.remove(this)
    }

    companion object {
        private const val JOB_ID = 1000
        fun trigger(context: Context) = enqueueWork(
                context, AutoSwipeJobIntentService::class.java, JOB_ID, Intent())
        }
}
