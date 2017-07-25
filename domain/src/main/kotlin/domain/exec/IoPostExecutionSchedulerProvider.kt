package domain.exec

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

internal object IoPostExecutionSchedulerProvider : PostExecutionSchedulerProvider {
    override fun provideScheduler(): Scheduler = Schedulers.io()
}
