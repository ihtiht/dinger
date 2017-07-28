package app.alarm

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.firebase.crash.FirebaseCrash
import domain.autoswipe.ImmediatePostAutoSwipeUseCase
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import org.stoyicker.dinger.R

internal class AlarmBannerActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_banner)
        executeImmediateSwipeSession()
    }

    private fun executeImmediateSwipeSession() = ImmediatePostAutoSwipeUseCase(this).execute(
            object : CompletableObserver {
                override fun onComplete() { }

                override fun onSubscribe(disposable: Disposable) { }

                override fun onError(error: Throwable) = FirebaseCrash.report(error)
            }
    )

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, AlarmBannerActivity::class.java)
    }
}
