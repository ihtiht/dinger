package data.autoswipe

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import data.notification.GroupNotification
import data.notification.NotificationManager
import data.notification.NotificationManagerModule
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = arrayOf(NotificationManagerModule::class, FirebaseCrashReporterModule::class))
internal class AutoSwipeReportHandlerModule {
    @Provides
    @Singleton
    fun autoSwipeReportHandler(
            notificationManager: NotificationManager,
            groupNotification: GroupNotification,
            crashReporter: CrashReporter) = {
        AutoSwipeReportHandler(notificationManager, groupNotification, crashReporter)
    }
}
