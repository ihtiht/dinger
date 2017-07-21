package app.splash

import com.google.firebase.crash.FirebaseCrash
import domain.auth.LoggedInUserCheckUseCase
import domain.exec.PostExecutionSchedulerProvider
import io.reactivex.observers.DisposableSingleObserver

internal class LoggedInCheckCoordinator(
        private val postExecutionSchedulerProvider: PostExecutionSchedulerProvider,
        private val callback: ResultCallback) {

    fun actionDoCheck() {
        LoggedInUserCheckUseCase(postExecutionSchedulerProvider).execute(
                object : DisposableSingleObserver<Boolean>() {
                    override fun onSuccess(t: Boolean) {
                        when (t) {
                            true -> callback.onLoggedInUserFound()
                            false -> callback.onLoggedInUserNotFound()
                        }
                    }

                    override fun onError(throwable: Throwable?) {
                        throwable?.let { FirebaseCrash.report(it) }
                        callback.onLoggedInUserNotFound()
                    }
                })
    }

    interface ResultCallback {
        fun onLoggedInUserFound()

        fun onLoggedInUserNotFound()
    }
}
