package data.autoswipe

import domain.autoswipe.FromErrorPostAutoSwipeUseCase
import domain.interactor.DisposableUseCase
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import reporter.CrashReporter
import java.text.SimpleDateFormat
import java.util.Locale

internal class FromErrorPostAutoSwipeAction(private val crashReporter: CrashReporter)
    : AutoSwipeJobIntentService.Action<Unit>()  {
    private var useCaseDelegate: DisposableUseCase? = null

    override fun execute(owner: AutoSwipeJobIntentService, callback: Unit) =
            FromErrorPostAutoSwipeUseCase(owner, Schedulers.trampoline()).let {
                useCaseDelegate = it
                it.execute(object : DisposableCompletableObserver() {
                    override fun onComplete() = commonDelegate.onComplete(owner)

                    override fun onError(error: Throwable) = commonDelegate.onError(owner, error)
                })
            }.also {
                crashReporter.report(FromErrorPostAutoSwipeTrackedException(
                        "Erroring autoswipe for ${360000 / 1000 / 60} minutes from " +
                                "${SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH)
                                        .format(System.currentTimeMillis())}."))
            }

    override fun dispose() {
        useCaseDelegate?.dispose()
    }

    private class FromErrorPostAutoSwipeTrackedException(message: String) : Throwable(message)
}
