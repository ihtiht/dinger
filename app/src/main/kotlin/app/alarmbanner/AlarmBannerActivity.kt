package app.alarmbanner

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import app.MainApplication
import org.stoyicker.dinger.R
import javax.inject.Inject

internal class AlarmBannerActivity : Activity() {
    @Inject
    lateinit var alarmBannerCoordinator: AlarmBannerCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_banner)
        inject()
        alarmBannerCoordinator.actionDoSchedule()
    }

    private fun inject() = (application as MainApplication).applicationComponent
            .newAlarmBannerComponent(AlarmBannerModule(this))
            .inject(this)

    override fun onDestroy() {
        super.onDestroy()
        alarmBannerCoordinator.actionCancelSchedule()
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, AlarmBannerActivity::class.java)
    }
}
