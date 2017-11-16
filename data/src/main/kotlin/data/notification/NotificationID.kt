package data.notification

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import org.stoyicker.dinger.data.R
import java.util.concurrent.atomic.AtomicInteger

internal object NotificationID {
    fun next(context: Context): Int {
        val prefs = context.getSharedPreferences(
                context.getString(R.string.preferences_file_notifications), Context.MODE_PRIVATE)
        AtomicInteger(retrieveCurrent(prefs)).incrementAndGet().let {
            saveCurrent(prefs, it)
            return it
        }
    }

    private fun retrieveCurrent(prefs: SharedPreferences) = prefs.getInt(
            PREFERENCE_KEY_NOTIFICATION_ID, 0)

    @SuppressLint("ApplySharedPref") // Intended, otherwise notification overriding could occur
    private fun saveCurrent(prefs: SharedPreferences, value: Int) {
        prefs.edit().putInt(PREFERENCE_KEY_NOTIFICATION_ID, value).commit()
    }

    private const val PREFERENCE_KEY_NOTIFICATION_ID = "org.stoyicker.dinger.NOTIFICATION_ID"
}