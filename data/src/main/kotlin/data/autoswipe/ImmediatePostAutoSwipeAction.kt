package data.autoswipe

import domain.autoswipe.ImmediatePostAutoSwipeUseCase
import domain.interactor.DisposableUseCase
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import reporter.CrashReporter

internal class ImmediatePostAutoSwipeAction(crashReporter: CrashReporter)
    : AutoSwipeJobIntentService.Action<Unit>(crashReporter)  {
    private var useCaseDelegate: DisposableUseCase? = null

    override fun execute(owner: AutoSwipeJobIntentService, callback: Unit) =
        ImmediatePostAutoSwipeUseCase(owner, Schedulers.trampoline()).let {
            useCaseDelegate = it
            it.execute(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    commonDelegate.onComplete(owner)
                }
                override fun onError(error: Throwable) { commonDelegate.onError(error, owner) }
            })
        }

    override fun dispose() {
        useCaseDelegate?.dispose()
    }
}
