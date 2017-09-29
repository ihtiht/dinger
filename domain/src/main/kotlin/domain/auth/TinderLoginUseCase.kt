package domain.auth

import com.facebook.login.LoginManager
import domain.interactor.CompletableDisposableUseCase
import io.reactivex.Completable
import io.reactivex.Scheduler

class TinderLoginUseCase(
        private val facebookId: String,
        private val facebookToken: String,
        asyncExecutionScheduler: Scheduler,
        postExecutionScheduler: Scheduler)
    : CompletableDisposableUseCase(asyncExecutionScheduler, postExecutionScheduler) {
    override fun buildUseCase(): Completable = LoginHolder.loginRecommendationProvider
            .login(DomainAuthRequestParameters(facebookId, facebookToken))
            .doOnSuccess {
                if (!AuthHolder.accountManager.addAccount(facebookId, it.apiKey)) {
                    throw FailedLoginException(
                        "Failed to add account $facebookId with token $facebookToken")
                }
            }
            .doFinally { LoginManager.getInstance().logOut() }
            .toCompletable()
}
