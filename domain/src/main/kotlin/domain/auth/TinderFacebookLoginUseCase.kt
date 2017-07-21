package domain.auth

import domain.Domain
import domain.exec.PostExecutionSchedulerProvider
import domain.interactor.CompletableUseCase
import io.reactivex.Completable

class TinderFacebookLoginUseCase(
        private val facebookId: String,
        private val facebookToken: String,
        postExecutionSchedulerProvider: PostExecutionSchedulerProvider)
    : CompletableUseCase(postExecutionSchedulerProvider) {
    override fun buildUseCase(): Completable = Domain.facadeProvider.tinderApiRepository()
            .login(DomainAuthRequestParameters(facebookId, facebookToken))
            .doOnSuccess({ Domain.accountManager.addAccount(facebookId, it.apiKey) })
            .toCompletable()
}
