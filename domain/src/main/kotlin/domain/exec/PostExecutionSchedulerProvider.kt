package domain.exec

import io.reactivex.Scheduler

/**
 * Describes the thread where the results of an UseCase will be published.
 * @see domain.interactor.UseCase
 */
interface PostExecutionSchedulerProvider {
    /**
     * The underlying scheduler to feed the observable from the UseCase into.
     */
    fun provideScheduler(): Scheduler
}
