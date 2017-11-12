package data.autoswipe

import domain.autoswipe.FromRateLimitedPostAutoSwipeUseCase
import domain.interactor.DisposableUseCase
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import reporter.CrashReporter

internal class FromRateLimitedPostAutoSwipeAction(
        crashReporter: CrashReporter,
        private val notBeforeMillis: Long)
    : AutoSwipeJobIntentService.Action<Unit>(crashReporter)  {
    private var useCaseDelegate: DisposableUseCase? = null

    override fun execute(owner: AutoSwipeJobIntentService, callback: Unit) =
        FromRateLimitedPostAutoSwipeUseCase(owner, Schedulers.trampoline(), notBeforeMillis).let {
            useCaseDelegate = it
            it.execute(object : DisposableCompletableObserver() {
                override fun onComplete() = commonDelegate.onComplete(owner)
                override fun onError(error: Throwable) = commonDelegate.onError(error, owner)
            })
        }

    override fun dispose() {
        useCaseDelegate?.dispose()
    }
}
