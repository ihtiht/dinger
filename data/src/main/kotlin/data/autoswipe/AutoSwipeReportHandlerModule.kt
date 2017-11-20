package data.autoswipe

import dagger.Module
import dagger.Provides
import data.notification.GroupNotification
import data.notification.NotificationManager
import data.notification.NotificationManagerModule
import javax.inject.Singleton

@Module(includes = arrayOf(NotificationManagerModule::class))
internal class AutoSwipeReportHandlerModule {
    @Provides
    @Singleton
    fun autoSwipeReportHandler(
            notificationManager: NotificationManager,
            groupNotification: GroupNotification) = {
        AutoSwipeReportHandler(notificationManager, groupNotification)
    }
}
