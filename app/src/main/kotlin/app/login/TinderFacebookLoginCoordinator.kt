package app.login

import domain.auth.TinderFacebookLoginUseCase
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableCompletableObserver
import reporter.CrashReporter

internal class TinderFacebookLoginCoordinator(
        private val view: TinderLoginView,
        private val asyncExecutionScheduler: Scheduler,
        private val postExecutionScheduler: Scheduler,
        private val resultCallback: ResultCallback,
        private val crashReporter: CrashReporter) {
    private var useCase: TinderFacebookLoginUseCase? = null

    fun actionDoLogin(facebookId: String, facebookToken: String) {
        view.setRunning()
        useCase = TinderFacebookLoginUseCase(
                facebookId, facebookToken, asyncExecutionScheduler, postExecutionScheduler)
        useCase?.execute(object : DisposableCompletableObserver() {
            override fun onError(error: Throwable) {
                crashReporter.report(error)
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
