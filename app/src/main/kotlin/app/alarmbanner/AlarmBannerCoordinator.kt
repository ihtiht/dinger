package app.alarmbanner

import android.content.Context
import com.google.firebase.crash.FirebaseCrash
import domain.autoswipe.ImmediatePostAutoSwipeUseCase
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

internal class AlarmBannerCoordinator(private val context: Context) {
    private var useCase: ImmediatePostAutoSwipeUseCase? = null

    fun actionDoSchedule() {
        useCase = ImmediatePostAutoSwipeUseCase(context, Schedulers.trampoline())
        useCase?.execute(object : DisposableCompletableObserver() {
            override fun onError(error: Throwable) = FirebaseCrash.report(error)

            override fun onComplete() {}
        })
    }

    fun actionCancelSchedule() {
        useCase?.dispose()
    }
}
