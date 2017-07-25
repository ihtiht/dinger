package data.alarm

import android.content.Context
import dagger.Module
import dagger.Provides
import data.RootModule
import javax.inject.Singleton

@Module(includes = arrayOf(RootModule::class))
internal class AlarmModule {
    @Provides
    @Singleton
    fun facadeProvider(context: Context) = AppAlarmManagerImpl(context)
}
