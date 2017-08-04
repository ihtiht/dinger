package app.splash

import android.content.Context
import com.google.firebase.crash.FirebaseCrash
import domain.auth.LoggedInUserCheckUseCase
import domain.exec.PostExecutionSchedulerProvider
import io.reactivex.observers.DisposableSingleObserver
import org.stoyicker.dinger.R

internal class LoggedInCheckCoordinator(
        private val context: Context,
        private val postExecutionSchedulerProvider: PostExecutionSchedulerProvider,
        private val resultCallback: ResultCallback) {
    private var useCase: LoggedInUserCheckUseCase? = null

    fun actionDoCheck() {
        useCase = LoggedInUserCheckUseCase(postExecutionSchedulerProvider)
        useCase?.execute(object : DisposableSingleObserver<Boolean>() {
            override fun onSuccess(t: Boolean) {
                when (t) {
                    true -> resultCallback.onLoggedInUserFound()
                    false -> resultCallback.onLoggedInUserNotFound()
                }
            }

            override fun onError(error: Throwable) {
                FirebaseCrash.report(IllegalStateException(context.getString(
                        R.string.account_check_should_always_succeed)))
                resultCallback.onLoggedInUserNotFound()
            }
        })
    }

    fun actionCancelCheck() {
        useCase?.dispose()
    }

    interface ResultCallback {
        fun onLoggedInUserFound()

        fun onLoggedInUserNotFound()
    }
}
