package data.autoswipe

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import data.notification.NotificationManager
import data.notification.NotificationManagerModule
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = arrayOf(NotificationManagerModule::class, FirebaseCrashReporterModule::class))
internal class AutoSwipeReportHandlerModule {
    @Provides
    @Singleton
    fun autoSwipeReportHandler(
            notificationManager: NotificationManager, crashReporter: CrashReporter) =
            AutoSwipeReportHandler(notificationManager, crashReporter)
}
