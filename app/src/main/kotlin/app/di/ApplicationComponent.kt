package app.di

import app.alarmbanner.AlarmBannerComponent
import app.alarmbanner.AlarmBannerModule
import app.login.TinderFacebookLoginComponent
import app.login.TinderFacebookLoginModule
import app.splash.LoggedInCheckComponent
import app.splash.LoggedInCheckModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(ApplicationModule::class))
@Singleton
internal interface ApplicationComponent {
    fun newSplashComponent(loggedInCheckModule: LoggedInCheckModule): LoggedInCheckComponent
    fun newTinderFacebookLoginComponent(tinderFacebookLoginModule: TinderFacebookLoginModule)
            : TinderFacebookLoginComponent
    fun newAlarmBannerComponent(alarmBannerModule: AlarmBannerModule)
            : AlarmBannerComponent
}
