package domain.auth

import domain.interactor.SingleDisposableUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

class LoggedInUserCheckUseCase(
        postExecutionScheduler: Scheduler)
    : SingleDisposableUseCase<Boolean>(postExecutionScheduler) {
    override fun buildUseCase(): Single<Boolean>
            = Single.just(AuthHolder.accountManager.isThereALoggedInUser())
}
