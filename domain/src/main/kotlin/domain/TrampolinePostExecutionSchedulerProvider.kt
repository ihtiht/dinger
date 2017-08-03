package domain

import domain.exec.PostExecutionSchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

object TrampolinePostExecutionSchedulerProvider : PostExecutionSchedulerProvider {
    override fun provideScheduler(): Scheduler = Schedulers.trampoline()
}
