package data.autoswipe

import android.content.Context
import android.content.Intent
import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.support.v4.app.JobIntentService
import com.google.firebase.crash.FirebaseCrash
import domain.autoswipe.DelayedPostAutoSwipeUseCase
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import java.util.*

internal class AutoSwipeJobIntentService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        val format = DateFormat.getPatternInstance(DateFormat.HOUR24_MINUTE_SECOND, Locale.ENGLISH)
        FirebaseCrash.report(RuntimeException(
                "AutoSwipe reporting in: ${(format as SimpleDateFormat).format(Date())}"))
        scheduleHappyPath()
    }

    private fun scheduleHappyPath() = DelayedPostAutoSwipeUseCase(this).execute(
            object : CompletableObserver {
                override fun onComplete() {}

                override fun onSubscribe(disposable: Disposable?) { }

                override fun onError(error: Throwable) = FirebaseCrash.report(error)
            })

    companion object {
        private const val JOB_ID = 1000
        fun trigger(context: Context) = enqueueWork(
                context, AutoSwipeJobIntentService::class.java, JOB_ID, Intent())
    }
}
