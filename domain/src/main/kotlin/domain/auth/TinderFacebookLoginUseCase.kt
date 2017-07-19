package domain.auth

import domain.exec.PostExecutionSchedulerProvider
import domain.interactor.CompletableUseCase
import io.reactivex.Completable

class TinderFacebookLoginUseCase(
        facebookId: String,
        facebookToken: String,
        postExecutionSchedulerProvider: PostExecutionSchedulerProvider)
    : CompletableUseCase(postExecutionSchedulerProvider) {
    override fun buildUseCase(): Completable {
        TODO("Use case for Tinder login")
    }
}
