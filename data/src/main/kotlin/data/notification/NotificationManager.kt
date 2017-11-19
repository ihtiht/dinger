package data.notification

import android.app.PendingIntent
import android.support.annotation.StringRes
import android.support.v4.app.NotificationCompat

internal interface NotificationManager {
    fun notify(@StringRes channelName: Int,
               @StringRes title: Int,
               @StringRes body: Int,
               @NotificationCategory category: String,
               groupName: String? = null,
               isGroupSummary: Boolean = false,
               @NotificationPriority priority: Long = NotificationManager.PRIORITY_MEDIUM,
               @NotificationVisibility visibility: Long = NotificationManager.VISIBILITY_PUBLIC,
               clickHandler: PendingIntent? = null)

    fun notify(@StringRes channelName: Int,
               title: String,
               body: String,
               @NotificationCategory category: String,
               groupName: String? = null,
               isGroupSummary: Boolean = false,
               @NotificationPriority priority: Long = NotificationManager.PRIORITY_MEDIUM,
               @NotificationVisibility visibility: Long = NotificationManager.VISIBILITY_PUBLIC,
               clickHandler: PendingIntent? = null)

    companion object {
        const val CATEGORY_RECOMMENDATION = NotificationCompat.CATEGORY_RECOMMENDATION
        const val CATEGORY_SERVICE = NotificationCompat.CATEGORY_SERVICE
        const val PRIORITY_MEDIUM = NotificationCompat.PRIORITY_DEFAULT.toLong()
        const val PRIORITY_LOW = NotificationCompat.PRIORITY_LOW.toLong()
        const val PRIORITY_HIGH = NotificationCompat.PRIORITY_HIGH.toLong()
        const val VISIBILITY_PRIVATE = NotificationCompat.VISIBILITY_PRIVATE.toLong()
        const val VISIBILITY_PUBLIC = NotificationCompat.VISIBILITY_PUBLIC.toLong()
        const val VISIBILITY_SECRET = NotificationCompat.VISIBILITY_SECRET.toLong()
    }
}
