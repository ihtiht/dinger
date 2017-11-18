package data.notification

import android.content.Context

internal interface GroupNotification {
    /**
     * @return Whether this notification is already showing or not.
     */
    fun isGroupShown(context: Context, groupName: String): Boolean

    fun markGroupAsNotShown(context: Context, groupName: String)
}
