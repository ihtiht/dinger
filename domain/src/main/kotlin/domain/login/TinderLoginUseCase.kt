package domain.login

import domain.interactor.CompletableDisposableUseCase
import io.reactivex.Completable
import io.reactivex.Scheduler

class TinderLoginUseCase(
        private val facebookId: String,
        private val facebookToken: String,
        asyncExecutionScheduler: Scheduler? = null,
        postExecutionScheduler: Scheduler)
    : CompletableDisposableUseCase(asyncExecutionScheduler, postExecutionScheduler) {
    override fun buildUseCase(): Completable {
        dispose()
        return LoginHolder.loginProvider
                .login(DomainAuthRequestParameters(facebookId, facebookToken))
                .doOnSuccess {
                    if (!LoginHolder.accountManagementProvider.updateOrAddAccount(
                            facebookId = facebookId,
                            facebookToken = facebookToken,
                            tinderApiKey = it.apiKey)) {
                        throw FailedLoginException(
                                "Failed to add account $facebookId with token $facebookToken")
                    }
                }
                .toCompletable()
    }
}
