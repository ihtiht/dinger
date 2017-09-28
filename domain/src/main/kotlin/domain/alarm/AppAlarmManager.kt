package domain.alarm

import android.content.Intent

abstract class AppAlarmManager {
    abstract fun delayBroadcastOneShot(requestCode: Int, delayMillis: Long, task: Intent)
}
