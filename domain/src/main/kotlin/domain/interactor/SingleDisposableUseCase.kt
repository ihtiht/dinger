package domain.interactor

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleDisposableUseCase<T> internal constructor(
        private val postExecutionScheduler: Scheduler)
    : DisposableUseCase(), UseCase<Single<T>> {
    fun execute(subscriber: DisposableSingleObserver<T>) {
        assembledSubscriber = buildUseCase()
                .subscribeOn(UseCase.defaultUseCaseScheduler)
                .observeOn(postExecutionScheduler)
                .subscribeWith(subscriber)
    }
}
