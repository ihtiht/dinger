package app.splash

import android.content.Context
import app.di.PerActivity
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
@PerActivity
internal class LoggedInCheckModule(
        private val context: Context,
        private val resultCallback: LoggedInCheckCoordinator.ResultCallback) {
    @Provides
    fun coordinator(
            @Named("io") asyncExecutionScheduler: Scheduler,
            @Named("main") postExecutionScheduler: Scheduler)
            = LoggedInCheckCoordinator(
            context,
            asyncExecutionScheduler,
            postExecutionScheduler,
            resultCallback)
}
