package domain.interactor

import domain.Domain
import domain.exec.PostExecutionSchedulerProvider
import io.reactivex.Flowable
import io.reactivex.subscribers.DisposableSubscriber

/**
 * Abstraction used to represent domain needs.
 */
abstract class UseCase<T>(private val postExecutionSchedulerProvider: PostExecutionSchedulerProvider) {
    private lateinit var assembledSubscriber: DisposableSubscriber<T>

    /**
     * Defines the observable that represents this use case.
     */
    protected abstract fun buildUseCaseFlowable(): Flowable<T>

    /**
     * Executes the use case.
     * @param subscriber The subscriber to notify of the results.
     */
    fun execute(subscriber: DisposableSubscriber<T>) {
        assembledSubscriber = buildUseCaseFlowable()
                .subscribeOn(Domain.useCaseScheduler)
                .observeOn(postExecutionSchedulerProvider.provideScheduler())
                .subscribeWith(subscriber)
    }

    /**
     * Tears down the use case.
     */
      fun terminate() {
          if (!assembledSubscriber.isDisposed) {
            assembledSubscriber.dispose()
          }
      }
}
