package data.autoswipe

import android.app.IntentService
import android.content.Intent
import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import com.google.firebase.crash.FirebaseCrash
import domain.autoswipe.DelayedPostAutoSwipeUseCase
import io.reactivex.observers.DisposableCompletableObserver
import java.util.*

internal class AutoSwipeIntentService : IntentService(AutoSwipeIntentService::class.java.name) {
    override fun onHandleIntent(intent: Intent?) {
        val format = DateFormat.getPatternInstance(DateFormat.HOUR24_MINUTE_SECOND, Locale.ENGLISH)
        FirebaseCrash.report(RuntimeException(
                "AutoSwipe reporting in ${(format as SimpleDateFormat).toLocalizedPattern()}"))
        DelayedPostAutoSwipeUseCase(this).execute(object : DisposableCompletableObserver() {
            override fun onComplete() { }

            override fun onError(error: Throwable?) {
                error?.let { FirebaseCrash.report(it) }
            }
        })
    }
}
