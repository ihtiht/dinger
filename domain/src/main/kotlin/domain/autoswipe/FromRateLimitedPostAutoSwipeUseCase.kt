package domain.autoswipe

import android.content.Context
import io.reactivex.Scheduler
import org.stoyicker.dinger.domain.R

class FromRateLimitedPostAutoSwipeUseCase(
        context: Context,
        postExecutionScheduler: Scheduler,
        private val notBeforeMillis: Long)
    : PostAutoSwipeUseCase(context, postExecutionScheduler) {
    override fun provideNotBeforeMillis(context: Context) =
            notBeforeMillis + context.resources.getInteger(R.integer.sweep_safe_ms).toLong()
}
