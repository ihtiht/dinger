package app.splash

import app.di.PerActivity
import dagger.Module
import dagger.Provides
import domain.exec.PostExecutionSchedulerProvider

@Module
@PerActivity
internal class LoggedInCheckModule(private val callback: LoggedInCheckCoordinator.ResultCallback) {
    @Provides
    fun coordinator(postExecutionSchedulerProvider: PostExecutionSchedulerProvider)
            = LoggedInCheckCoordinator(postExecutionSchedulerProvider, callback)
}
