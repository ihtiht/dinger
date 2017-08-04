package domain.autoswipe

import android.content.Context
import android.support.annotation.IntRange
import domain.alarm.AlarmHolder
import domain.interactor.CompletableDisposableUseCase
import io.reactivex.Completable
import io.reactivex.Scheduler

abstract class PostAutoSwipeUseCase internal constructor(
        private val context: Context,
        preExecutionScheduler: Scheduler? = null,
        postExecutionScheduler: Scheduler)
    : CompletableDisposableUseCase(preExecutionScheduler, postExecutionScheduler) {
    @IntRange(from = 0, to = Long.MAX_VALUE)
    internal abstract fun provideDelayMillis(context: Context): Long

    override fun buildUseCase(): Completable = Completable.fromCallable {
        AlarmHolder.alarmManager.delayBroadcastOneShot(
                REQUEST_CODE,
                provideDelayMillis(context),
                AutoSwipeHolder.autoSwipeLauncherFactory.newFromBroadcast(context))
    }

    private companion object {
        const val REQUEST_CODE = 0
    }
}
