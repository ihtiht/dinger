package app

import domain.exec.PostExecutionSchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

internal class UiPostExecutionSchedulerProvider : PostExecutionSchedulerProvider {
    override fun provideScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
