package app.di

import app.UiPostExecutionSchedulerProvider
import dagger.Module
import dagger.Provides
import domain.exec.PostExecutionSchedulerProvider
import javax.inject.Singleton

@Module
@Singleton
internal class ApplicationModule() {
    @Provides
    @Singleton
    fun uiSchedulerProvider(): PostExecutionSchedulerProvider = UiPostExecutionSchedulerProvider()
}
