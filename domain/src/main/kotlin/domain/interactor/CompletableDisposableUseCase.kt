package domain.interactor

import domain.DomainHolder
import domain.exec.PostExecutionSchedulerProvider
import io.reactivex.Completable
import io.reactivex.observers.DisposableCompletableObserver

abstract class CompletableDisposableUseCase(
        private val postExecutionSchedulerProvider: PostExecutionSchedulerProvider)
    : DisposableUseCase(), UseCase<Completable> {
    fun execute(subscriber: DisposableCompletableObserver) {
        assembledSubscriber = buildUseCase()
                .subscribeOn(DomainHolder.useCaseScheduler)
                .observeOn(postExecutionSchedulerProvider.provideScheduler())
                .subscribeWith(subscriber)
    }
}
