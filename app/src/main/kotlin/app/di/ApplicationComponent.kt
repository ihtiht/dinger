package app.di

import app.alarmbanner.AlarmBannerComponent
import app.alarmbanner.AlarmBannerModule
import app.crash.FirebaseCrashReporterModule
import app.login.TinderLoginComponent
import app.login.TinderLoginModule
import app.splash.SplashComponent
import app.splash.SplashModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(ApplicationModule::class, FirebaseCrashReporterModule::class))
@Singleton
internal interface ApplicationComponent {
    fun newSplashComponent(splashModule: SplashModule): SplashComponent
    fun newTinderLoginComponent(tinderLoginModule: TinderLoginModule): TinderLoginComponent
    fun newAlarmBannerComponent(alarmBannerModule: AlarmBannerModule): AlarmBannerComponent
}
