package app.login

import app.UIPostExecutionSchedulerProvider
import com.google.firebase.crash.FirebaseCrash
import domain.interactor.DisposableUseCase
import domain.interactor.TinderLoginUseCase
import io.reactivex.observers.DisposableCompletableObserver

internal class TinderFacebookLoginCoordinator(private val view: TinderLoginView) {
    private lateinit var useCase: DisposableUseCase

    fun actionDoLogin(facebookId: String, facebookToken: String) {
        view.show()
        TinderLoginUseCase(facebookId, facebookToken, UIPostExecutionSchedulerProvider)
                .execute(object : DisposableCompletableObserver() {
                    override fun onError(e: Throwable?) {
                        e.takeIf { it != null }.also { FirebaseCrash.report(e) }
                        view.hide()
                    }

                    override fun onComplete() {
                        // TODO dologin
                        view.hide()
                    }
                })
    }

    fun actionCancelLogin() {
        view.show()
        useCase.dispose()
        // TODO cancelLogin
    }
}
