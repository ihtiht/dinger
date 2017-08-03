package domain.autoswipe

import android.content.Context
import android.support.annotation.IntRange
import domain.alarm.AlarmHolder
import domain.exec.PostExecutionSchedulerProvider
import domain.interactor.CompletableDisposableUseCase
import io.reactivex.Completable

abstract class PostAutoSwipeUseCase internal constructor(
        private val context: Context,
        postExecutionSchedulerProvider: PostExecutionSchedulerProvider)
    : CompletableDisposableUseCase(postExecutionSchedulerProvider) {
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
