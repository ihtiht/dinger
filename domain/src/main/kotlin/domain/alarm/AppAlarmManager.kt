package domain.alarm

import android.content.Intent

abstract class AppAlarmManager {
    abstract fun delayServiceOneShot(requestCode: Int, delayMillis: Long, task: Intent): Unit
}
