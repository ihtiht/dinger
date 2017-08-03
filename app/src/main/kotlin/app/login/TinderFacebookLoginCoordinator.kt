package app.login

import com.google.firebase.crash.FirebaseCrash
import domain.auth.TinderFacebookLoginUseCase
import domain.exec.PostExecutionSchedulerProvider
import io.reactivex.observers.DisposableCompletableObserver

internal class TinderFacebookLoginCoordinator(
        private val view: TinderLoginView,
        private val postExecutionSchedulerProvider: PostExecutionSchedulerProvider,
        private val resultCallback: ResultCallback) {
    private var useCase: TinderFacebookLoginUseCase? = null

    fun actionDoLogin(facebookId: String, facebookToken: String) {
        view.setRunning()
        useCase = TinderFacebookLoginUseCase(
                facebookId, facebookToken, postExecutionSchedulerProvider)
        useCase?.execute(object : DisposableCompletableObserver() {
            override fun onError(error: Throwable) {
                FirebaseCrash.report(error)
                view.setError()
            }

            override fun onComplete() {
                resultCallback.onTinderLoginSuccess()
                view.setStale()
            }
        })
    }

    fun actionCancelLogin() {
        view.setStale()
        useCase?.dispose()
    }

    interface ResultCallback {
        fun onTinderLoginSuccess()
    }
}
