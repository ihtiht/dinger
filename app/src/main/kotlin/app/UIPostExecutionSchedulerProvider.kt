package app

import domain.exec.PostExecutionSchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

internal object UIPostExecutionSchedulerProvider : PostExecutionSchedulerProvider {
    override fun provideScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
