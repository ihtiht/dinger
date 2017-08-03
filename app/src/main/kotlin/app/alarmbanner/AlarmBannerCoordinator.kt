package app.alarmbanner

import android.content.Context
import com.google.firebase.crash.FirebaseCrash
import domain.TrampolinePostExecutionSchedulerProvider
import domain.autoswipe.ImmediatePostAutoSwipeUseCase
import domain.exec.PostExecutionSchedulerProvider
import io.reactivex.observers.DisposableCompletableObserver

internal class AlarmBannerCoordinator(
        private val context: Context,
        private val postExecutionSchedulerProvider: PostExecutionSchedulerProvider) {
    private var useCase: ImmediatePostAutoSwipeUseCase? = null

    fun actionDoSchedule() {
        useCase = ImmediatePostAutoSwipeUseCase(context, TrampolinePostExecutionSchedulerProvider)
        useCase?.execute(object : DisposableCompletableObserver() {
            override fun onError(error: Throwable) = FirebaseCrash.report(error)

            override fun onComplete() { }
        })
    }

    fun actionCancelSchedule() {
        useCase?.dispose()
    }
}
