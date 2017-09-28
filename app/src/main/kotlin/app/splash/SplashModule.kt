package app.splash

import app.di.PerActivity
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import reporter.CrashReporter
import javax.inject.Named

@Module
@PerActivity
internal class SplashModule(
        private val resultCallback: LoggedInCheckCoordinator.ResultCallback) {
    @Provides
    fun coordinator(
            @Named("io") asyncExecutionScheduler: Scheduler,
            @Named("main") postExecutionScheduler: Scheduler,
            crashReporter: CrashReporter) =
            LoggedInCheckCoordinator(
                    asyncExecutionScheduler,
                    postExecutionScheduler,
                    resultCallback,
                    crashReporter)
}
