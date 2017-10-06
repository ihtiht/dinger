package app.login

import domain.login.TinderLoginUseCase
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableCompletableObserver
import reporter.CrashReporter

internal class TinderLoginCoordinator constructor(
        private val view: TinderLoginView,
        private val asyncExecutionScheduler: Scheduler,
        private val postExecutionScheduler: Scheduler,
        private val resultCallback: ResultCallback,
        private val crashReporter: CrashReporter) {
    private var useCase: TinderLoginUseCase? = null

    fun actionDoLogin(facebookId: String, facebookToken: String) {
        view.setRunning()
        useCase = TinderLoginUseCase(
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
