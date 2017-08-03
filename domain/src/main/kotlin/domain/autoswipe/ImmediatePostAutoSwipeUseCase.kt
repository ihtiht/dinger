package domain.autoswipe

import android.content.Context
import domain.exec.PostExecutionSchedulerProvider
import org.stoyicker.dinger.domain.R

class ImmediatePostAutoSwipeUseCase(
        context: Context,
        postExecutionSchedulerProvider: PostExecutionSchedulerProvider)
    : PostAutoSwipeUseCase(context, postExecutionSchedulerProvider) {
    override fun provideDelayMillis(context: Context) = context.resources.getInteger(
            R.integer.sweep_immediate_delay_ms).toLong()
}
