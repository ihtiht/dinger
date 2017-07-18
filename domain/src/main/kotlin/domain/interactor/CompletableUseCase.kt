package domain.interactor

import domain.Domain
import domain.exec.PostExecutionSchedulerProvider
import io.reactivex.Completable
import io.reactivex.observers.DisposableCompletableObserver

abstract class CompletableUseCase(
        private val postExecutionSchedulerProvider: PostExecutionSchedulerProvider)
    : UseCase<Completable>, DisposableUseCase() {
    fun execute(subscriber: DisposableCompletableObserver) {
        assembledSubscriber = buildUseCase()
                .subscribeOn(Domain.useCaseScheduler)
                .observeOn(postExecutionSchedulerProvider.provideScheduler())
                .subscribeWith(subscriber)
    }
}
