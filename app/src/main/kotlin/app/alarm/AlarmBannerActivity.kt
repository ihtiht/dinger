package app.alarm

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.firebase.crash.FirebaseCrash
import domain.autoswipe.ImmediatePostAutoSwipeUseCase
import io.reactivex.observers.DisposableCompletableObserver
import org.stoyicker.dinger.R

internal class AlarmBannerActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_banner)
        executeImmediateSwipeSession()
    }

    private fun executeImmediateSwipeSession() = ImmediatePostAutoSwipeUseCase(this).execute(
            object : DisposableCompletableObserver() {
                override fun onComplete() { }

                override fun onError(error: Throwable?)
                        = error?.let { FirebaseCrash.report(it) } ?: Unit
            }
    )

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, AlarmBannerActivity::class.java)
    }
}
