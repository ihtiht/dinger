package data.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import domain.alarm.AppAlarmManager
import javax.inject.Inject

internal class ServiceAlarmManager @Inject constructor(private val context: Context)
    : AppAlarmManager<Intent>() {
    override fun delayOneShot(requestCode: Int, delayMillis: Long, task: Intent) =
        context.getSystemService(AlarmManager::class.java).set(requestCode, delayMillis,
                PendingIntent.getService(context, requestCode, task, PendingIntent.FLAG_ONE_SHOT))
}
