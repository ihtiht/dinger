package domain.interactor

import domain.DomainHolder
import domain.exec.PostExecutionSchedulerProvider
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleDisposableUseCase<T>(
        private val postExecutionSchedulerProvider: PostExecutionSchedulerProvider)
    : DisposableUseCase(), UseCase<Single<T>> {
    fun execute(subscriber: DisposableSingleObserver<T>) {
        assembledSubscriber = buildUseCase()
                .subscribeOn(DomainHolder.useCaseScheduler)
                .observeOn(postExecutionSchedulerProvider.provideScheduler())
                .subscribeWith(subscriber)
    }
}
