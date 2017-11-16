package data.notification

import android.content.Context
import dagger.Module
import dagger.Provides
import data.RootModule
import javax.inject.Singleton

@Module(includes = arrayOf(RootModule::class))
internal class NotificationManagerModule {
    @Provides
    @Singleton
    fun autoSwipeReportHandler(context: Context): NotificationManager =
            NotificationManagerImpl(context)
}
