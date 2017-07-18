package app.login

import app.ApplicationComponent
import com.google.firebase.crash.FirebaseCrash
import domain.exec.PostExecutionSchedulerProvider
import domain.interactor.DisposableUseCase
import domain.interactor.TinderLoginUseCase
import io.reactivex.observers.DisposableCompletableObserver
import javax.inject.Inject

internal class TinderFacebookLoginCoordinator(
        private val view: TinderLoginView,
        applicationComponent: ApplicationComponent) {
    @Inject
    lateinit var postExecutionSchedulerProvider: PostExecutionSchedulerProvider
    private lateinit var useCase: DisposableUseCase

    init {
        applicationComponent.inject(this)
    }

    fun actionDoLogin(facebookId: String, facebookToken: String) {
        view.setRunning()
        TinderLoginUseCase(facebookId, facebookToken, postExecutionSchedulerProvider)
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
