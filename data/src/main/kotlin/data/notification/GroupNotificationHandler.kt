package data.notification

import android.content.Context
import org.stoyicker.dinger.data.R

internal object GroupNotificationHandler {
    /**
     * @return Whether this notification is already showing or not.
     */
    fun isGroupShown(context: Context, groupName: String): Boolean =
        context.getSharedPreferences(context.getString(
                R.string.preferences_file_notifications), Context.MODE_PRIVATE).let {
            return if (!it.getBoolean(groupName, false)) {
                it.edit().putBoolean(groupName, true).apply()
                false
            } else {
                true
            }
        }
}
