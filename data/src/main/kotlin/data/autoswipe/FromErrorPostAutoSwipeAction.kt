package data.autoswipe

import data.autoswipe.AutoSwipeJobIntentService.CommonResultDelegate
import domain.autoswipe.FromErrorPostAutoSwipeUseCase
import domain.interactor.DisposableUseCase
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

internal class FromErrorPostAutoSwipeAction(private val owner: AutoSwipeJobIntentService)
    : AutoSwipeJobIntentService.Action  {
    private var useCaseDelegate: DisposableUseCase? = null
    private val resultDelegate = CommonResultDelegate(this)

    override fun execute() {
        FromErrorPostAutoSwipeUseCase(owner, Schedulers.trampoline()).apply {
            useCaseDelegate = this
            execute(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    resultDelegate.onComplete(owner)
                }
                override fun onError(error: Throwable) {
                    resultDelegate.onError(owner, error)
                }
            })
        }
    }

    override fun dispose() {
        useCaseDelegate?.dispose()
    }
}
