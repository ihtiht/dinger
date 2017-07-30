package domain.auth

import com.facebook.login.LoginManager
import domain.DomainHolder
import domain.exec.PostExecutionSchedulerProvider
import domain.interactor.CompletableDisposableUseCase
import io.reactivex.Completable

class TinderFacebookLoginUseCase(
        private val facebookId: String,
        private val facebookToken: String,
        postExecutionSchedulerProvider: PostExecutionSchedulerProvider)
    : CompletableDisposableUseCase(postExecutionSchedulerProvider) {
    override fun buildUseCase(): Completable = DomainHolder.facadeProvider.tinderApiRepository()
            .login(DomainAuthRequestParameters(facebookId, facebookToken))
            .doOnSuccess {
                if (!AuthHolder.accountManager.addAccount(facebookId, it.apiKey)) {
                    throw FailedLoginException(
                        "Failed to add account $facebookId with token $facebookToken")
                }
            }
            .doOnSuccess { LoginManager.getInstance().logOut() }
            .toCompletable()
}
