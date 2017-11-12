package domain.alarm

import android.content.Intent

abstract class AppAlarmManager {
    abstract fun setBroadcastOneShotFor(requestCode: Int, notBeforeMillis: Long, task: Intent)
}
