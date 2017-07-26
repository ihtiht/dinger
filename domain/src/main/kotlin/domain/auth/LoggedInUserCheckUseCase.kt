package domain.auth

import domain.exec.PostExecutionSchedulerProvider
import domain.interactor.SingleDisposableUseCase
import io.reactivex.Single

class LoggedInUserCheckUseCase(
        postExecutionSchedulerProvider: PostExecutionSchedulerProvider)
    : SingleDisposableUseCase<Boolean>(postExecutionSchedulerProvider) {
    override fun buildUseCase(): Single<Boolean>
            = Single.just(AuthHolder.accountManager.isThereALoggedInUser())
}
