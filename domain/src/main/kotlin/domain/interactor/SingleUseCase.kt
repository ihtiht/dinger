package domain.interactor

import domain.Domain
import domain.exec.PostExecutionSchedulerProvider
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleUseCase<T>(
        private val postExecutionSchedulerProvider: PostExecutionSchedulerProvider)
    : DisposableUseCase(), UseCase<Single<T>> {
    fun execute(subscriber: DisposableSingleObserver<T>) {
        assembledSubscriber = buildUseCase()
                .subscribeOn(Domain.useCaseScheduler)
                .observeOn(postExecutionSchedulerProvider.provideScheduler())
                .subscribeWith(subscriber)
    }
}
