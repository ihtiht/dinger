package data.notification

import android.content.Context
import org.stoyicker.dinger.data.R

internal class GroupNotificationImpl : GroupNotification {
    override fun isGroupShown(context: Context, groupName: String): Boolean =
            "group_$groupName".let { key ->
                context.getSharedPreferences(context.getString(
                R.string.preferences_file_notifications), Context.MODE_PRIVATE).let {
                    return if (!it.getBoolean(key, false)) {
                        it.edit().putBoolean(key, true).apply()
                        false
                    } else {
                        true
                    }
        }
    }
}
