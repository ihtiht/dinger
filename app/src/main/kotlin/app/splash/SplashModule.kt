package app.splash

import android.app.Activity
import app.di.PerActivity
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import reporter.CrashReporter
import tracker.EventTracker
import javax.inject.Named

@Module
@PerActivity
internal class SplashModule(
        private val activity: Activity,
        private val loggedInCheckResultCallback: LoggedInCheckCoordinator.ResultCallback,
        private val userEmailPropertySetterCoordinatorResultCallback
        : UserEmailPropertySetterCoordinator.ResultCallback) {
    @Provides
    fun loggedInCheckCoordinator(
            @Named("io") asyncExecutionScheduler: Scheduler,
            @Named("main") postExecutionScheduler: Scheduler,
            crashReporter: CrashReporter) = LoggedInCheckCoordinator(
            asyncExecutionScheduler,
            postExecutionScheduler,
            loggedInCheckResultCallback,
            crashReporter)

    @Provides
    fun splashEventTracker(eventTracker: EventTracker) = SplashEventTracker(activity, eventTracker)

    @Provides
    fun userEmailPropertySetterCoordinator(splashEventTracker: SplashEventTracker) =
            UserEmailPropertySetterCoordinator(
            activity, splashEventTracker, userEmailPropertySetterCoordinatorResultCallback)
}
