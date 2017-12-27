package data.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import domain.alarm.AppAlarmManager

internal class AppAlarmManagerImpl(private val context: Context)
    : AppAlarmManager() {
    override fun setBroadcastOneShotFor(requestCode: Int, notBeforeMillis: Long, task: Intent) =
            (context.getSystemService(Context.ALARM_SERVICE) as AlarmManager).set(
                    AlarmManager.RTC_WAKEUP,
                    notBeforeMillis,
                    PendingIntent.getBroadcast(
                            context,
                            requestCode,
                            task,
                            PendingIntent.FLAG_ONE_SHOT or
                                    PendingIntent.FLAG_CANCEL_CURRENT))
}
