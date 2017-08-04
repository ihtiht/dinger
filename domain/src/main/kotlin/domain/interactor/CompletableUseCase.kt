package domain.interactor

import domain.DomainHolder
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Scheduler

abstract class CompletableUseCase internal constructor(
        private val postExecutionScheduler: Scheduler)
    : UseCase<Completable> {
    fun execute(subscriber: CompletableObserver) {
        buildUseCase()
                .subscribeOn(UseCase.defaultUseCaseScheduler)
                .observeOn(postExecutionScheduler)
                .subscribeWith(subscriber)
    }
}
