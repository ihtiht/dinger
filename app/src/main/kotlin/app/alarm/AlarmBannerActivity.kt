package app.alarm

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.stoyicker.dinger.R

internal class AlarmBannerActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_banner)
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, AlarmBannerActivity::class.java)
    }
}
