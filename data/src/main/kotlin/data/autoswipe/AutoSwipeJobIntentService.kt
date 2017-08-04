package data.autoswipe

import android.content.Context
import android.content.Intent
import android.support.v4.app.JobIntentService
import com.google.firebase.crash.FirebaseCrash
import domain.autoswipe.DelayedPostAutoSwipeUseCase
import domain.autoswipe.FromErrorPostAutoSwipeUseCase
import domain.interactor.DisposableUseCase
import domain.recommendation.DomainRecommendationCollection
import domain.recommendation.GetRecommendationsUseCase
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

internal class AutoSwipeJobIntentService : JobIntentService() {
    private val disposableUseCases = mutableSetOf<DisposableUseCase>()

    override fun onHandleWork(intent: Intent) {
        GetRecommendationsUseCase(Schedulers.trampoline(), Schedulers.trampoline()).apply {
            disposableUseCases.add(this)
            execute(object : DisposableSingleObserver<DomainRecommendationCollection>() {
                override fun onSuccess(payload: DomainRecommendationCollection) {
                    FirebaseCrash.report(IllegalStateException("$payload"))
                    clearUseCase(this@apply)
                    scheduleHappyPath()
                }

                override fun onError(error: Throwable) {
                    FirebaseCrash.report(error)
                    clearUseCase(this@apply)
                    scheduleFromErrorPath()
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableUseCases.apply {
            map { it.dispose() }
            clear()
        }
    }

    private fun scheduleHappyPath() = DelayedPostAutoSwipeUseCase(
            this, Schedulers.trampoline(), Schedulers.trampoline()).apply {
        disposableUseCases.add(this)
        execute(object : DisposableCompletableObserver() {
            override fun onComplete() {
                clearUseCase(this@apply)
            }

            override fun onError(error: Throwable) {
                FirebaseCrash.report(error)
                clearUseCase(this@apply)
            }
        })
    }

    private fun scheduleFromErrorPath() = FromErrorPostAutoSwipeUseCase(
            this, Schedulers.trampoline(), Schedulers.trampoline()).apply {
        disposableUseCases.add(this)
        execute(object : DisposableCompletableObserver() {
            override fun onComplete() {
                clearUseCase(this@apply)
            }

            override fun onError(error: Throwable) {
                FirebaseCrash.report(error)
                clearUseCase(this@apply)
            }
        })
    }

    private fun clearUseCase(useCase: DisposableUseCase) = useCase.apply {
        dispose()
        disposableUseCases.remove(this)
    }

    companion object {
    private const val JOB_ID = 1000
    fun trigger(context: Context) = enqueueWork(
            context, AutoSwipeJobIntentService::class.java, JOB_ID, Intent())
    }
}
