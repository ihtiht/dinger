package domain.autoswipe

import android.content.Context
import io.reactivex.Scheduler
import org.stoyicker.dinger.domain.R

class DelayedPostAutoSwipeUseCase(
        context: Context,
        preExecutionScheduler: Scheduler,
        postExecutionScheduler: Scheduler)
    : PostAutoSwipeUseCase(context, preExecutionScheduler, postExecutionScheduler) {
    override fun provideDelayMillis(context: Context) = context.resources.getInteger(
            R.integer.sweep_delayed_delay_ms).toLong()
}
