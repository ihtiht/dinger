package domain.interactor

import domain.DomainHolder
import domain.exec.PostExecutionSchedulerProvider
import io.reactivex.Completable
import io.reactivex.CompletableObserver

abstract class CompletableUseCase internal constructor(
        private val postExecutionSchedulerProvider: PostExecutionSchedulerProvider)
    : UseCase<Completable> {
    fun execute(subscriber: CompletableObserver) {
        buildUseCase()
                .subscribeOn(DomainHolder.useCaseScheduler)
                .observeOn(postExecutionSchedulerProvider.provideScheduler())
                .subscribeWith(subscriber)
    }
}
