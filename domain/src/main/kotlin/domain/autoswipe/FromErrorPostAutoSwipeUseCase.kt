package domain.autoswipe

import android.content.Context
import io.reactivex.Scheduler
import org.stoyicker.dinger.domain.R

class FromErrorPostAutoSwipeUseCase(
        context: Context,
        preExecutionScheduler: Scheduler,
        postExecutionScheduler: Scheduler)
    : PostAutoSwipeUseCase(context, preExecutionScheduler, postExecutionScheduler) {
    override fun provideDelayMillis(context: Context) = context.resources.getInteger(
            R.integer.sweep_from_error_delay_ms).toLong()
}
