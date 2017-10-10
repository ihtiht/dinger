package data.autoswipe

import domain.autoswipe.DelayedPostAutoSwipeUseCase
import domain.interactor.DisposableUseCase
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import reporter.CrashReporter
import java.text.SimpleDateFormat
import java.util.Locale

internal class DelayedPostAutoSwipeAction(private val crashReporter: CrashReporter)
    : AutoSwipeJobIntentService.Action<Unit>()  {
    private var useCaseDelegate: DisposableUseCase? = null

    override fun execute(owner: AutoSwipeJobIntentService, callback: Unit) =
        DelayedPostAutoSwipeUseCase(owner, Schedulers.trampoline()).let {
            useCaseDelegate = it
            it.execute(object : DisposableCompletableObserver() {
                override fun onComplete() = commonDelegate.onComplete(owner)
                override fun onError(error: Throwable) = commonDelegate.onError(owner, error)
            })
        }.also {
            crashReporter.report(DelayedPostAutoSwipeTrackedException(
                    "Delaying autoswipe for ${45000000 / 1000 / 60} minutes from " +
                            "${SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH)
                                    .format(System.currentTimeMillis())}."))
        }

    override fun dispose() {
        useCaseDelegate?.dispose()
    }

    private class DelayedPostAutoSwipeTrackedException(message: String) : Throwable(message)
}
