package data.autoswipe

import domain.autoswipe.FromErrorPostAutoSwipeUseCase
import domain.interactor.DisposableUseCase
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

internal class FromErrorPostAutoSwipeAction : AutoSwipeJobIntentService.Action<Unit>()  {
    private var useCaseDelegate: DisposableUseCase? = null

    override fun execute(owner: AutoSwipeJobIntentService, callback: Unit) =
            FromErrorPostAutoSwipeUseCase(owner, Schedulers.trampoline()).let {
                useCaseDelegate = it
                it.execute(object : DisposableCompletableObserver() {
                    override fun onComplete() {
                        commonDelegate.onComplete(owner)
                    }

                    override fun onError(error: Throwable) {
                        commonDelegate.onError(owner, error)
                    }
                })
            }

    override fun dispose() {
        useCaseDelegate?.dispose()
    }
}
