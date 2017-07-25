package domain.autoswipe

import android.content.Context
import android.support.annotation.IntRange
import domain.alarm.AlarmHolder
import domain.exec.IoPostExecutionSchedulerProvider
import domain.interactor.CompletableUseCase
import io.reactivex.Completable

abstract class PostAutoSwipeUseCase internal constructor(internal val context: Context)
    : CompletableUseCase(IoPostExecutionSchedulerProvider) {
    @IntRange(from = 0, to = Long.MAX_VALUE)
    internal abstract fun provideDelayMillis(): Long

    override fun buildUseCase(): Completable = Completable.fromCallable {
        AlarmHolder.alarmManager.delayServiceOneShot(
                REQUEST_CODE,
                provideDelayMillis(),
                AutoSwipeHolder.autoSwipeIntentFactory.newIntent(context))
    }

    private companion object {
        const val REQUEST_CODE = 0
    }
}
