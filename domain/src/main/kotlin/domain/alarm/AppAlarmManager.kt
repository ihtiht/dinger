package domain.alarm

import android.content.Intent

abstract class AppAlarmManager internal constructor() {
    abstract fun delayBroadcastOneShot(requestCode: Int, delayMillis: Long, task: Intent): Unit
}
