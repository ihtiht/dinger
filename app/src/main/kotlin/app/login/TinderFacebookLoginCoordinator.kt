package app.login

import com.google.firebase.crash.FirebaseCrash
import domain.auth.TinderFacebookLoginUseCase
import domain.exec.PostExecutionSchedulerProvider
import domain.interactor.DisposableUseCase
import io.reactivex.observers.DisposableCompletableObserver

internal class TinderFacebookLoginCoordinator(
        private val view: TinderLoginView,
        private val postExecutionSchedulerProvider: PostExecutionSchedulerProvider) {
    private lateinit var useCase: DisposableUseCase

    fun actionDoLogin(facebookId: String, facebookToken: String) {
        view.setRunning()
        TinderFacebookLoginUseCase(facebookId, facebookToken, postExecutionSchedulerProvider)
                .execute(object : DisposableCompletableObserver() {
                    override fun onError(e: Throwable?) {
                        e.takeIf { it != null }.also { FirebaseCrash.report(e) }
                        view.setStale()
                    }

                    override fun onComplete() {
                        // TODO dologin
                        view.setStale()
                    }
                })
    }

    fun actionCancelLogin() {
        view.setStale()
        useCase.dispose()
        // TODO cancelLogin
    }
}
