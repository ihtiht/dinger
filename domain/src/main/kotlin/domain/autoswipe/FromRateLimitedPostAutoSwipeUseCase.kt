package domain.autoswipe

import android.content.Context
import io.reactivex.Scheduler
import java.util.Random
import java.util.concurrent.TimeUnit

class FromRateLimitedPostAutoSwipeUseCase(
        context: Context,
        postExecutionScheduler: Scheduler,
        private val notBeforeMillis: Long)
    : PostAutoSwipeUseCase(context, postExecutionScheduler) {
    override fun notBeforeMillis(context: Context) = notBeforeMillis +
            TimeUnit.HOURS.convert((Random().nextInt(5 - 1) + 1).toLong(),
                    TimeUnit.MILLISECONDS)
}
