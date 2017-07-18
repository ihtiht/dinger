package domain.exec

import io.reactivex.Scheduler

/**
 * Describes the thread where the results of an DisposableUseCase will be published.
 * @see domain.interactor.DisposableUseCase
 */
interface PostExecutionSchedulerProvider {
    /**
     * The underlying scheduler to feed the observable from the DisposableUseCase into.
     */
    fun provideScheduler(): Scheduler
}
