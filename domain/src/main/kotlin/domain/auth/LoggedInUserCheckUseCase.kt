package domain.auth

import domain.Domain
import domain.exec.PostExecutionSchedulerProvider
import domain.interactor.SingleUseCase
import io.reactivex.Single

class LoggedInUserCheckUseCase(
        postExecutionSchedulerProvider: PostExecutionSchedulerProvider)
    : SingleUseCase<Boolean>(postExecutionSchedulerProvider) {
    override fun buildUseCase(): Single<Boolean>
            = Single.just(Domain.accountManager.isThereALoggedInUser())
}
