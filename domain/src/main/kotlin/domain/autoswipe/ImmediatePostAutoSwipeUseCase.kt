package domain.autoswipe

import android.content.Context
import io.reactivex.Scheduler

class ImmediatePostAutoSwipeUseCase(
        context: Context,
        postExecutionScheduler: Scheduler)
    : PostAutoSwipeUseCase(context, postExecutionScheduler) {
    override fun provideNotBeforeMillis(context: Context) = System.currentTimeMillis()
}
