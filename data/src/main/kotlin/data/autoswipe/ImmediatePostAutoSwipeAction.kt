package data.autoswipe

import domain.autoswipe.ImmediatePostAutoSwipeUseCase
import domain.interactor.DisposableUseCase
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import reporter.CrashReporter
import java.text.SimpleDateFormat
import java.util.Locale

internal class ImmediatePostAutoSwipeAction(private val crashReporter: CrashReporter)
    : AutoSwipeJobIntentService.Action<Unit>()  {
    private var useCaseDelegate: DisposableUseCase? = null

    override fun execute(owner: AutoSwipeJobIntentService, callback: Unit) =
        ImmediatePostAutoSwipeUseCase(owner, Schedulers.trampoline()).let {
            useCaseDelegate = it
            it.execute(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    commonDelegate.onComplete(owner)
                }
                override fun onError(error: Throwable) {
                    commonDelegate.onError(owner, error)
                }
            })
        }.also {
            crashReporter.report(ImmediatePostAutoSwipeTrackedException(
                    "Immediate autoswipe from " +
                            "${SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH)
                                    .format(System.currentTimeMillis())}."))
        }

    override fun dispose() {
        useCaseDelegate?.dispose()
    }

    private class ImmediatePostAutoSwipeTrackedException(message: String) : Throwable(message)
}
