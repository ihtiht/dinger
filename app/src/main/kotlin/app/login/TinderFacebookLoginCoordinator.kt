package app.login

import app.UIPostExecutionSchedulerProvider
import com.google.firebase.crash.FirebaseCrash
import domain.interactor.DisposableUseCase
import domain.interactor.TinderLoginUseCase
import io.reactivex.observers.DisposableCompletableObserver

internal class TinderFacebookLoginCoordinator(private val view: TinderLoginView) {
    private lateinit var useCase: DisposableUseCase

    fun actionDoLogin(facebookId: String, facebookToken: String) {
        view.setRunning()
        TinderLoginUseCase(facebookId, facebookToken, UIPostExecutionSchedulerProvider)
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
