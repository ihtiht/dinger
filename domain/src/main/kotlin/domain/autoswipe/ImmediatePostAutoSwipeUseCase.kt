package domain.autoswipe

import android.content.Context
import io.reactivex.Scheduler
import org.stoyicker.dinger.domain.R

class ImmediatePostAutoSwipeUseCase(
        context: Context,
        postExecutionScheduler: Scheduler)
    : PostAutoSwipeUseCase(context, postExecutionScheduler) {
    override fun provideDelayMillis(context: Context) = context.resources.getInteger(
            R.integer.sweep_immediate_delay_ms).toLong()
}
