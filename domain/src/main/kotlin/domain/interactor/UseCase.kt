package domain.interactor

import io.reactivex.schedulers.Schedulers

internal interface UseCase<out T> {
    fun buildUseCase(): T

    companion object {
        val defaultUseCaseScheduler = Schedulers.io()
    }
}
