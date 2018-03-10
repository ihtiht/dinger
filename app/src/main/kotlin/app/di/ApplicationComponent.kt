package app.di

import app.alarmbanner.AlarmBannerComponent
import app.alarmbanner.AutoSwipeTriggerModule
import app.alarmbanner.ContinueModule
import app.crash.CrashReporterModule
import app.event.FirebaseEventTrackerModule
import app.tinder.login.TinderLoginComponent
import app.tinder.login.TinderLoginModule
import app.splash.SplashComponent
import app.splash.SplashModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    SchedulerModule::class,
    CrashReporterModule::class,
    FirebaseEventTrackerModule::class])
@Singleton
internal interface ApplicationComponent {
    fun newSplashComponent(splashModule: SplashModule): SplashComponent
    fun newTinderLoginComponent(tinderLoginModule: TinderLoginModule): TinderLoginComponent
    fun newAlarmBannerComponent(
            autoSwipeTriggerModule: AutoSwipeTriggerModule,
            continueModule: ContinueModule): AlarmBannerComponent
}
