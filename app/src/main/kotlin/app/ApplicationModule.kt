package app

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
internal class ApplicationModule {
    @Provides
    @Singleton
    fun uiSchedulerProvider() = UiPostExecutionSchedulerProvider()
}
