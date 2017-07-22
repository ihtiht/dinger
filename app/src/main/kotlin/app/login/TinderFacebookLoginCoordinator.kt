package app.login

import com.google.firebase.crash.FirebaseCrash
import domain.auth.TinderFacebookLoginUseCase
import domain.exec.PostExecutionSchedulerProvider
import io.reactivex.observers.DisposableCompletableObserver

internal class TinderFacebookLoginCoordinator(
        private val view: TinderLoginView,
        private val postExecutionSchedulerProvider: PostExecutionSchedulerProvider) {
    private lateinit var useCase: TinderFacebookLoginUseCase

    fun actionDoLogin(facebookId: String, facebookToken: String) {
        view.setRunning()
        useCase= TinderFacebookLoginUseCase(
                facebookId, facebookToken, postExecutionSchedulerProvider)
        useCase.execute(object : DisposableCompletableObserver() {
            override fun onError(exception: Throwable?) {
                exception?.let { FirebaseCrash.report(exception) }
                view.setError()
            }

            override fun onComplete() {
                view.setStale()
            }
        })
    }

    fun actionCancelLogin() {
        view.setStale()
        useCase.dispose()
    }
}
