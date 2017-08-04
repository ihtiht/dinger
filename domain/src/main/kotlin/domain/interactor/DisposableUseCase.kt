package domain.interactor

import io.reactivex.disposables.Disposable

abstract class DisposableUseCase internal constructor() {
    internal var assembledSubscriber: Disposable? = null
    /**
     * Tears down the use case if required.
     */
      fun dispose() {
          if (!(assembledSubscriber?.isDisposed ?: true)) {
            assembledSubscriber!!.dispose()
          }
      }
}
