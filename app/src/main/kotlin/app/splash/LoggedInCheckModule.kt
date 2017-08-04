package app.splash

import android.content.Context
import app.di.PerActivity
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler

@Module
@PerActivity
internal class LoggedInCheckModule(
        private val context: Context,
        private val resultCallback: LoggedInCheckCoordinator.ResultCallback) {
    @Provides
    fun coordinator(postExecutionScheduler: Scheduler)
            = LoggedInCheckCoordinator(context, postExecutionScheduler, resultCallback)
}
