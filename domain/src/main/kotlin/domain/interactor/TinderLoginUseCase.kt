package domain.interactor

import domain.exec.PostExecutionSchedulerProvider
import io.reactivex.Completable

class TinderLoginUseCase(
        facebookId: String,
        facebookToken: String,
        postExecutionSchedulerProvider: PostExecutionSchedulerProvider)
    : CompletableUseCase(postExecutionSchedulerProvider) {
    override fun buildUseCase(): Completable {
        TODO("Use case for Tinder login")
    }
}
