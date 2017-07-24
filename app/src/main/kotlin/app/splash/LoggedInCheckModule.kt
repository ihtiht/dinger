package app.splash

import android.content.Context
import app.di.PerActivity
import dagger.Module
import dagger.Provides
import domain.exec.PostExecutionSchedulerProvider

@Module
@PerActivity
internal class LoggedInCheckModule(
        private val context: Context,
        private val resultCallback: LoggedInCheckCoordinator.ResultCallback) {
    @Provides
    fun coordinator(postExecutionSchedulerProvider: PostExecutionSchedulerProvider)
            = LoggedInCheckCoordinator(context, postExecutionSchedulerProvider, resultCallback)
}
