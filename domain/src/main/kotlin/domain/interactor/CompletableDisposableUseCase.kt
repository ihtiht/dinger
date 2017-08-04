package domain.interactor

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableCompletableObserver

abstract class CompletableDisposableUseCase internal constructor(
        private val executionScheduler: Scheduler? = null,
        private val postExecutionScheduler: Scheduler)
    : DisposableUseCase(), UseCase<Completable> {
    fun execute(subscriber: DisposableCompletableObserver) {
        assembledSubscriber = buildUseCase()
                .subscribeOn(executionScheduler ?: UseCase.defaultUseCaseScheduler)
                .observeOn(postExecutionScheduler)
                .subscribeWith(subscriber)
    }
}
