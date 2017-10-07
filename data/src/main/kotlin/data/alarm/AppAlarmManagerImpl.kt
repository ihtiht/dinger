package data.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import domain.alarm.AppAlarmManager
import javax.inject.Inject

internal class AppAlarmManagerImpl @Inject constructor(private val context: Context)
    : AppAlarmManager() {
    override fun delayBroadcastOneShot(requestCode: Int, delayMillis: Long, task: Intent) =
            (context.getSystemService(Context.ALARM_SERVICE) as AlarmManager).set(
                    AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + delayMillis,
                    PendingIntent.getBroadcast(
                            context,
                            requestCode,
                            task,
                            PendingIntent.FLAG_ONE_SHOT))
}
