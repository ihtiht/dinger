package data.autoswipe

import data.autoswipe.AutoSwipeJobIntentService.CommonResultDelegate
import domain.autoswipe.DelayedPostAutoSwipeUseCase
import domain.interactor.DisposableUseCase
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

internal class DelayedPostAutoSwipeAction : AutoSwipeJobIntentService.Action<Unit>  {
    private var useCaseDelegate: DisposableUseCase? = null
    private val resultDelegate = CommonResultDelegate(this)

    override fun execute(owner: AutoSwipeJobIntentService, callback: Unit) =
        DelayedPostAutoSwipeUseCase(owner, Schedulers.trampoline()).let {
            useCaseDelegate = it
            it.execute(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    resultDelegate.onComplete(owner)
                }
                override fun onError(error: Throwable) {
                    resultDelegate.onError(owner, error)
                }
            })
        }

    override fun dispose() {
        useCaseDelegate?.dispose()
    }
}
